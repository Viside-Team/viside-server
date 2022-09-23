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

    private MyPageService myPageService;

    @GetMapping("/profile")
    public ResponseEntity<Map<String, String>> getUserProfile(Principal principal){
        log.info("id: " + principal.getName()); //userId 표시 O
        String userName = myPageService.getUsername(principal.getName()); //NPE

        Map<String, String> response = new HashMap<>();
        response.put("username", userName);

        return ResponseEntity.ok().body(response);
    }
}
