package com.vside.server.domain.auth.service;

import com.vside.server.domain.auth.dto.TokenInfoDto;
import com.vside.server.domain.auth.repository.RefreshTokenRepository;
import com.vside.server.jwt.TokenProvider;
import io.jsonwebtoken.Claims;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {

    private static final String ERR = "Refresh Token 만료 ! 재 로그인 해주세요.";

    private final TokenProvider tokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;


    public TokenInfoDto createToken(Authentication authentication){
        return new TokenInfoDto(
                authentication.getName(),
                tokenProvider.createAccessToken(authentication),
                createRefreshToken()
        );
    }

    private String createRefreshToken(){
        return UUID.randomUUID().toString();
    }

    public void saveToken(TokenInfoDto tokenInfo) {
        refreshTokenRepository.save(tokenInfo.getUserId(), tokenInfo.getRefreshToken());
    }

    public String handleToken(String refreshToken, Claims claims) {
        String userId = claims.getSubject();

        // 해당 userId key가 존재하며, 그 value의 토큰값과 일치하는가?
        if (!refreshTokenRepository.hasToken(userId) || refreshTokenRepository.tokenEquals(userId, refreshToken)){
            throw new IllegalStateException(ERR);
        }
        return reissue(claims);
    }

    private String reissue(Claims claims){
        // 기존의 만료된 jwt 기반으로 새로운 JWT 생성하기
        return tokenProvider.reissueToken(claims);
    }
}
