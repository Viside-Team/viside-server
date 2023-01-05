package com.vside.server.domain.user.controller;
import com.vside.server.domain.user.dto.JoinRequest;
import com.vside.server.domain.user.dto.JoinResponse;
import com.vside.server.domain.user.dto.nameCheckRequest;
import com.vside.server.domain.user.dto.nameCheckResponse;
import com.vside.server.domain.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {
    private final UserService userService;
    @PostMapping("/nameCheck")
    @ApiOperation(value = "닉네임 중복 체크 api", notes = "회원가입 성공/실패 여부 반환")
    public ResponseEntity<nameCheckResponse> join(@Valid @RequestBody nameCheckRequest nameCheckRequest){
        if (userService.exists(nameCheckRequest.getName()))
            return ResponseEntity.ok().body(new nameCheckResponse(true));
        return ResponseEntity.ok().body(new nameCheckResponse(false));
    }
}
