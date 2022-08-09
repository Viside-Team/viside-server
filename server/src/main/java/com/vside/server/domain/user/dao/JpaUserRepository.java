package com.vside.server.domain.user.dao;

import com.vside.server.domain.user.Entity.ViUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<ViUserEntity, Long> {
    Optional<ViUserEntity> findByUserId(long id);
    Optional<ViUserEntity> findByUserName(String name);
}
