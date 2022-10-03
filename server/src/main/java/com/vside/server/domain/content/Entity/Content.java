package com.vside.server.domain.content.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access  = AccessLevel.PROTECTED)
@Getter
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "content_link")
    private String contentLink;


    @Column(name = "content_title")
    private String contentTitle;

    @Column(name = "content_content")
    private String contentContent;

    @Column(name = "content_main_keyword")
    private String contentMainKeyword;

    @OneToMany(mappedBy = "content",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContentKeyword> contentKeywords = new ArrayList<>();

    @Builder
    public Content(String contentLink, String contentTitle, String contentMainKeyword, String contentKeyword){
        this.contentLink = contentLink;
        this.contentTitle = contentTitle;
        this.contentMainKeyword = contentMainKeyword;

    }

}