package com.vside.server.domain.user.service;

import com.vside.server.domain.user.Entity.ViUserEntity;
import com.vside.server.domain.user.dao.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
