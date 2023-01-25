package com.vside.server.domain.content.dao;

import com.vside.server.domain.content.Entity.Content;
import com.vside.server.domain.content.Entity.ContentKeyword;
import com.vside.server.domain.keyword.Entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Key;
import java.util.List;

public interface ContentRepository extends JpaRepository<Content,Long> {
    Content findByContentTitle(String content_title);
    Content findByContentId(Long content_id);
    List<Content> findByContentKeywordsIn(List<ContentKeyword> contentKeywords);
    List<Content> findByContentMainKeyword(Keyword keyword);
}
