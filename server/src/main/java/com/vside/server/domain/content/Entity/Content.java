package com.vside.server.domain.content.Entity;

import com.vside.server.domain.content.dto.ContentPageResponse;
import com.vside.server.domain.content.dto.ContentResponse;
import com.vside.server.domain.keyword.Entity.Keyword;
import com.vside.server.domain.scrap.dto.ScrapContentsDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access  = AccessLevel.PROTECTED)
@Getter
@ToString
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long contentId;

    @Lob
    @Column(name = "content_link")
    private String contentLink;


    @Column(name = "content_title")
    private String contentTitle;

    @Column(name = "content_main_keyword")
    private String contentMainKeyword;

    @Lob
    @Column(name = "content_image")
    private String imgLink;

    @Column
    private LocalDateTime uploadDate;

    @Lob
    @Column(name = "comtent_cover_image")
    private String coverImgUrl;

    @Column
    private boolean isBrightBg;

    @Lob
    @Column(name = "lighterColor")
    private String lighterColor;

    @Lob
    @Column(name = "darkerColor")
    private String darkerColor;

    @OneToMany(mappedBy = "content",cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ContentKeyword> contentKeywords = new ArrayList<>();

    @Builder
    public Content(String contentLink,
                   String contentTitle,
                   String contentMainKeyword,
                   String imgLink,
                   String coverImgUrl,
                   String lighterColor,
                   String darkerColor,
                   LocalDateTime uploadDate){
        this.contentLink = contentLink;
        this.contentTitle = contentTitle;
        this.contentMainKeyword = contentMainKeyword;
        this.imgLink = imgLink;
        this.uploadDate = uploadDate;
        this.coverImgUrl = coverImgUrl;
        this.darkerColor = darkerColor;
        this.lighterColor = lighterColor;
    }

    public void setBrightBg(String brightBg) {
        System.out.println(brightBg);
        this.isBrightBg = Objects.equals(brightBg, "true");
    }

    public ContentResponse entityToHomeContentDTO(
            Long contentId,
            String contentTitle,
            String contentLink,
            String contentMainKeyword,
            String coverImgUrl,
            String imgLink,
            List<ContentKeyword> contentKeywords,
            String lighterColor,
            String darkerColor,
            boolean isScrap
    ){
        return ContentResponse.builder()
                .contentId(contentId)
                .title(contentTitle)
                .contentLink(contentLink)
                .mainKeyword(contentMainKeyword)
                .coverImgUrl(coverImgUrl)
                .contentImgUrl(imgLink)
                .isScrap(isScrap)
                .darkerColor(darkerColor)
                .lighterColor(lighterColor)
                .keywords(contentKeywords
                        .stream()
                        .map(ContentKeyword::getKeyword)
                        .map(Keyword::getKeyword)
                        .collect(Collectors.toList()))
                .build();
    }
    public ContentPageResponse entityToContentPageDTO(
            Long contentId,
            String contentTitle,
            String contentMainKeyword,
            String imgUrl,
            List<ContentKeyword> contentKeywords,
            String contentLink,
            boolean isBrightBg,
            boolean isScrap
    ){
        return ContentPageResponse.builder()
                .contentId(contentId)
                .title(contentTitle)
                .mainKeyword(contentMainKeyword)
                .contentImgUrl(imgUrl)
                .isScrap(isScrap)
                .isBrightBg(isBrightBg)
                .contentLink(contentLink)
                .keywords(contentKeywords
                        .stream()
                        .map(ContentKeyword::getKeyword)
                        .map(Keyword::getKeyword)
                        .collect(Collectors.toList()))
                .build();
    }
    public ScrapContentsDTO entityToScrapContentDTO(
            Long contentId,
            String contentTitle,
            String contentLink,
            String coverImgUrl,
            String imgLink,
            List<ContentKeyword> contentKeywords,
            boolean isScrap
    ){
        return ScrapContentsDTO.builder()
                .contentId(contentId)
                .title(contentTitle)
                .contentLink(contentLink)
                .coverImgUrl(coverImgUrl)
                .contentImgUrl(imgLink)
                .keywords(contentKeywords
                        .stream()
                        .map(ContentKeyword::getKeyword)
                        .map(Keyword::getKeyword)
                        .collect(Collectors.toList()))
                .isScrap(isScrap)
                .build();

    }

}
