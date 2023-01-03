package com.vside.server.domain.mypage.controller;

import com.vside.server.domain.mypage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyPageController {
    private static final String RESPONSE_FIELD = "username";
    private static final String ANONYMOUS_USERNAME = "비밀스런 독서가";

    private final MyPageService myPageService;

    @GetMapping("/profile")
    public ResponseEntity<Map<String, String>> getUserProfile(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok().body(Map.of(RESPONSE_FIELD, ANONYMOUS_USERNAME));
        }

        String userName = myPageService.getUserProfile(principal.getName());
        return ResponseEntity.ok().body(Map.of(RESPONSE_FIELD, userName));
    }
}
