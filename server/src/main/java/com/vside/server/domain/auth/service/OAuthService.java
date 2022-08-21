package com.vside.server.domain.auth.service;

import com.vside.server.domain.user.Entity.User;
import com.vside.server.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService{

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public boolean exists(String provider, String snsId) {
        checkArgument(provider != null, "provider must be provided.");
        checkArgument(snsId != null, "snsId must be provided.");

        String type;
        if (provider.equals("kakao")) type = "k";
        else if (provider.equals("apple")) type = "a";
        else throw new IllegalArgumentException("지원되지 않는 로그인 형식입니다");

        return userRepository.countByLoginTypeAndSnsId(type, snsId) > 0;
    }

    @Transactional(readOnly = true)
    public User getExistingUser(String snsId) {
        return userRepository.findOneBySnsId(snsId);
    }
}
