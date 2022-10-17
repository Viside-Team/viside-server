package com.vside.server.domain.scrap.dao;

import com.vside.server.domain.scrap.Entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    boolean existsByContentContentIdAndUserUserId(Long contentId, Long userId);
}
