package com.vside.server.domain.user.controller;
import com.vside.server.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

//    @GetMapping("{specific}")
//    public ResponseEntity<Long> specific()
}
