package com.vside.server.domain.user.service;

import com.vside.server.domain.user.Entity.ViUserEntity;
import com.vside.server.domain.user.dao.JpaUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private JpaUserRepository jpaUserRepository;
    private ViUserEntity userEntity;
    @Test
    void user_test(){

        userEntity= ViUserEntity.builder().userName("test").build();
        jpaUserRepository.save(userEntity);
        Optional<ViUserEntity> user = jpaUserRepository.findByUserName("test");
        Assertions.assertThat(user.get().getUserName()).isEqualTo("test");
    }
}
