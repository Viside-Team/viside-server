package com.vside.server.domain.keyword.Entity;

import com.vside.server.domain.content.Entity.ContentKeyword;
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
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long keywordId;

    @Column(name = "keyword",unique = true)
    private String keyword;

    @OneToMany(mappedBy = "keyword",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContentKeyword> contentKeywords = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Keyword(String keyword){
        this.keyword = keyword;
    }
}
