package com.vside.server.domain.user.controller;
import com.vside.server.domain.user.dto.JoinRequest;
import com.vside.server.domain.user.dto.JoinResponse;
import com.vside.server.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/signin")
    @ApiOperation(value = "회원가입 (API 토큰 필요없음)")
    public ResponseEntity<JoinResponse> join(@Valid @RequestBody JoinRequest joinRequest){
        return ResponseEntity.ok().body(new JoinResponse(userService.join(joinRequest)));
    }
}
