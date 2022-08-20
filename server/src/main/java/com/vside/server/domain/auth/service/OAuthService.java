package com.vside.server.domain.auth.service;

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
    public boolean exists(String provider, String snsId) {
        String type = "k";
        if (provider.equals("apple")) type = "a";
        return userRepository.countByLoginTypeAndSnsId(type, snsId) > 0;
    }

}
