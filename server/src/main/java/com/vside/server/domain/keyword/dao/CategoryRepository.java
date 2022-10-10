package com.vside.server.domain.keyword.dao;

import com.vside.server.domain.keyword.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategory(String category);
    boolean existsCategoryByCategory(String category);

}
