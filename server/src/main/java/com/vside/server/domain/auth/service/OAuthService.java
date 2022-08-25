package com.vside.server.domain.auth.service;

import com.vside.server.domain.common.LoginType;
import com.vside.server.domain.user.Entity.User;
import com.vside.server.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService{

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public boolean exists(LoginType provider, String snsId) {
        return userRepository.countByLoginTypeAndSnsId(provider, snsId) > 0;
    }

    @Transactional(readOnly = true)
    public User getExistingUser(String snsId) {
        return userRepository.findOneBySnsId(snsId);
    }
}
