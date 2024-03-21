package com.vside.server.domain.user.service;

import com.vside.server.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public boolean exists(String name) {
        return userRepository.findByUserName(name).isPresent();
    }

}
