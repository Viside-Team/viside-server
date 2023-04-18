package com.vside.server.domain.auth.service;

import com.vside.server.domain.auth.dto.TokenInfoDto;
import com.vside.server.domain.auth.repository.RefreshTokenRepository;
import com.vside.server.jwt.TokenProvider;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

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
}
