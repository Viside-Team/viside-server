package com.vside.server.domain.mypage.service;

import com.vside.server.domain.user.Entity.User;
import com.vside.server.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public String getUserProfile(String id){

        Long userId = Long.parseLong(id);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 사용자"));
        return user.getUserName();
    }
}
