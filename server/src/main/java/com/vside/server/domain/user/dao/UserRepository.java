package com.vside.server.domain.user.dao;

import com.vside.server.domain.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneBySnsId(String snsId);
    long countByLoginTypeAndSnsId(String loginType, String snsId);
}
