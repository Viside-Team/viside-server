package com.vside.server.domain.auth.controller;

import com.vside.server.domain.auth.dto.LoginRequest;
import com.vside.server.domain.auth.dto.LoginResponse;
import com.vside.server.domain.auth.service.OAuthService;
import com.vside.server.domain.user.Entity.User;
import com.vside.server.jwt.TokenProvider;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;
    private final TokenProvider tokenProvider;

    /*
    로그인
    POST http://localhost:8080/login
     */
    @PostMapping("/login")
    @ApiOperation(value = "login (JWT 토큰 필요없음)", notes = "회원 여부와 jwt token을 반환합니다")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse res = new LoginResponse();
        boolean memStat = oAuthService.exists(loginRequest.getProvider(), loginRequest.getSnsId());
        if (memStat){
            User user = oAuthService.getExistingUser(loginRequest.getSnsId());
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);
            res.setJwt(jwt);
        }
        res.setMemberStatus(memStat);
        return ResponseEntity.ok().body(res);
    }
}
