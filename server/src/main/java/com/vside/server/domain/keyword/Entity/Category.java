package com.vside.server.domain.keyword.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access  = AccessLevel.PROTECTED)
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categorydId;

    @Column
    private String category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Keyword> keywords = new ArrayList<Keyword>();

    public void addCategory(Keyword keyword){
        keywords.add(keyword);
    }
    @Builder
    public Category(String category){
        this.category = category;
    }

}
