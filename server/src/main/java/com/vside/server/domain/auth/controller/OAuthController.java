package com.vside.server.domain.auth.controller;

import com.vside.server.domain.auth.dto.CheckMemberResponse;
import com.vside.server.domain.auth.dto.LoginRequest;
import com.vside.server.domain.auth.service.OAuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    /*
    authentication code로 첫 로그인 시 회원가입
    url: http://localhost:8080/login/kakao
    request:
     */
    @PostMapping("/login")
    @ApiOperation(value = "회원가입 여부 확인 (API 토큰 필요없음)")
    public ResponseEntity<CheckMemberResponse> checkMemberStat(@Valid @RequestBody LoginRequest loginRequest) {
        CheckMemberResponse res = new CheckMemberResponse(oAuthService.exists(loginRequest.getProvider(), loginRequest.getSnsId()));
        return ResponseEntity.ok().body(res);
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> authentication(@Valid @RequestBody LoginRequest loginRequest) {
//        if (!oAuthService.existsBySnsId(loginRequest.getSnsId())){
//            //회원가입 절차
//            //새로운 유저 등록 -> 등록 후 해당 사용자 로그인 절차 진행
//        }
//        //그게 아니면 로그인 절차
//        User user = oAuthService.getExistingUser(loginRequest.getSnsId());
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList("ROLE_USER"));
//        //Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        LoginResponse res = new LoginResponse(tokenProvider.createToken(authentication));
//        return ResponseEntity.ok().body(res);
//    }
}
