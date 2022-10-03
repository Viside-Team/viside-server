package com.vside.server.domain.keyword.dao;

import com.vside.server.domain.content.Entity.Content;
import com.vside.server.domain.keyword.Entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Keyword findByKeyword(String keyword);
}
