//package com.vside.server.domain.oauth.controller;
//
//import com.vside.server.domain.auth.controller.OAuthController;
//import com.vside.server.domain.auth.dto.LoginRequest;
//import com.vside.server.domain.auth.dto.LoginResponse;
//import com.vside.server.domain.auth.dto.WithdrawalRequestDto;
//import com.vside.server.domain.auth.dto.WithdrawalResponseDto;
//import com.vside.server.domain.auth.service.OAuthService;
//import com.vside.server.domain.common.LoginType;
//import com.vside.server.domain.user.Entity.User;
//import com.vside.server.domain.user.dao.UserRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//
//@SpringBootTest
//public class OauthControllerTest {
//    @Autowired
//    OAuthService oAuthService;
//    @Autowired
//    OAuthController oAuthController;
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    public void 회원탈퇴(){
//        User user = User.builder().userName("park").email("123@naver.com").loginType(LoginType.valueOf("KAKAO")).snsId("200").build();
//        userRepository.save(user);
//        ResponseEntity<WithdrawalResponseDto> res =  oAuthController.withdrawal(WithdrawalRequestDto.builder().snsId("200").build());
//        Assertions.assertThat(res.getBody().getMessage()).isEqualTo("success");
//        ResponseEntity<WithdrawalResponseDto> err_res =  oAuthController.withdrawal(WithdrawalRequestDto.builder().snsId("300").build());
//        Assertions.assertThat(err_res.getBody().getMessage()).isEqualTo("invalid_userid");
//    }
//}
