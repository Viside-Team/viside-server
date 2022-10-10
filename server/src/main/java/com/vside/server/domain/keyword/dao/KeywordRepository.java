package com.vside.server.domain.keyword.dao;

import com.vside.server.domain.keyword.Entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Keyword findByKeyword(String keyword);
    boolean existsKeywordByKeyword(String keyword);
}
