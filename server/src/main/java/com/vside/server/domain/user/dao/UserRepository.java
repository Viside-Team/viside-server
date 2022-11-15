package com.vside.server.domain.user.dao;

import com.vside.server.domain.common.LoginType;
import com.vside.server.domain.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneBySnsId(String snsId);
    Optional<User> findByUserName(String userName);
    long countByLoginTypeAndSnsId(LoginType loginType, String snsId);
    Optional<User> findBySnsId(String id);
}
