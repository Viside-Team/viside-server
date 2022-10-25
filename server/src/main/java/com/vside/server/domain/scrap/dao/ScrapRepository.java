package com.vside.server.domain.scrap.dao;

import com.vside.server.domain.content.Entity.Content;
import com.vside.server.domain.scrap.Entity.Scrap;
import com.vside.server.domain.user.Entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    boolean existsByContentContentIdAndUserUserId(Long contentId, Long userId);

    int countByUserUserId(Long userId);

    @Query(value = "select c from Scrap s, Content c where s.content=c and s.user.userId=:userId")
    List<Content> findScrapContentsByUserId(@Param("userId") Long userId);

    Scrap findByContentAndUser(Content content, User user);

    boolean existsByContentAndUser(Content content, User user);
}
