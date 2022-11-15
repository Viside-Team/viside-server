package com.vside.server.domain.user.service;

import com.vside.server.domain.common.LoginType;
import com.vside.server.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class UserService {
    UserRepository userRepository;
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public boolean exists(String name) {
        return userRepository.existsUserByUserName(name);
    }

}
