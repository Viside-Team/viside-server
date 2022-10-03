package com.vside.server.domain.content.dao;

import com.vside.server.domain.content.Entity.Content;
import com.vside.server.domain.content.Entity.ContentKeyword;
import com.vside.server.domain.keyword.Entity.Category;
import com.vside.server.domain.keyword.Entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Key;
import java.util.List;

public interface ContentKeywordReporitory extends JpaRepository<ContentKeyword,Long> {
//    List<ContentKeyword> findAllByKeyword(Keyword keyword);
    List<ContentKeyword> findAllByKeyword(Keyword keyword);
    List<ContentKeyword> findAllByContent(Content content);

}
