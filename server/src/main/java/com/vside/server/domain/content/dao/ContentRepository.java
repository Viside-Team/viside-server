package com.vside.server.domain.content.dao;

import com.vside.server.domain.content.Entity.Content;
import com.vside.server.domain.content.Entity.ContentKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content,Long> {
    Content findByContentTitle(String content_title);
}
