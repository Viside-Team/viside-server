package com.vside.server.domain.auth.controller;

import com.vside.server.domain.auth.dto.LoginResponse;
import com.vside.server.domain.user.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    /*
    authentication code로 첫 로그인 시 회원가입
    url: http://localhost:8080/login/oauth/kakao?code=********
     */
    @GetMapping("/login/oauth/{provider}")
    public ResponseEntity<LoginResponse> login(@PathVariable String provider, @RequestParam String code){
        LoginResponse loginResponse = oAuthService.login(provider, code);
        return ResponseEntity.ok().body(loginResponse);
    }
}
