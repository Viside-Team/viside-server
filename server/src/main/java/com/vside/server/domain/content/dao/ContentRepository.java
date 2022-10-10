package com.vside.server.domain.content.dao;

import com.vside.server.domain.content.Entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content,Long> {
    Content findByContentTitle(String content_title);
}
