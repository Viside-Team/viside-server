package com.vside.server.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class UserService {
//    private final JpaUserRepository jpaUserRepository;
//    public long getUserId(long id){
//        Optional<ViUserEntity> user = jpaUserRepository.findByUserId(0L);
//
//        return user.get().getUserId();
//    }

}
