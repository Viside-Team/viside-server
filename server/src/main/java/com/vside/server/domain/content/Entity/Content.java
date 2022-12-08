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

    @Lob
    @Column(name = "content_body")
    private String contentBody;

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

    @OneToMany(mappedBy = "content",cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ContentKeyword> contentKeywords = new ArrayList<>();

    @Builder
    public Content(String contentLink,
                   String contentTitle,
                   String contentMainKeyword,
                   String contentBody,
                   String imgLink,
                   String coverImgUrl,
                   boolean isBrightBg,
                   LocalDateTime uploadDate){
        this.contentLink = contentLink;
        this.contentTitle = contentTitle;
        this.contentMainKeyword = contentMainKeyword;
        this.contentBody = contentBody;
        this.imgLink = imgLink;
        this.uploadDate = uploadDate;
        this.coverImgUrl = coverImgUrl;
        this.isBrightBg = isBrightBg;
    }

    public void setBrightBg(String brightBg) {
        System.out.println(brightBg);
        this.isBrightBg = Objects.equals(brightBg, "true");
    }

    public ContentResponse entityToHomeContentDTO(
                                Long contentId,
                                String contentTitle,
                                String contentMainKeyword,
                                String imgLink,
                                List<ContentKeyword> contentKeywords,
                                boolean isScrap
                                ){
        return ContentResponse.builder()
                .contentId(contentId)
                .title(contentTitle)
                .mainKeyword(contentMainKeyword)
                .imgUrl(imgLink)
                .isScrap(isScrap)
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
            boolean isBrightBg,
            boolean isScrap
            ){
        return ContentPageResponse.builder()
                .contentId(contentId)
                .title(contentTitle)
                .mainKeyword(contentMainKeyword)
                .imgUrl(imgUrl)
                .isScrap(isScrap)
                .isBrightBg(isBrightBg)
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
            String imgLink,
            List<ContentKeyword> contentKeywords,
            boolean isScrap
    ){
        return ScrapContentsDTO.builder()
                .contentId(contentId)
                .title(contentTitle)
                .imgUrl(imgLink)
                .keywords(contentKeywords
                        .stream()
                        .map(ContentKeyword::getKeyword)
                        .map(Keyword::getKeyword)
                        .collect(Collectors.toList()))
                .isScrap(isScrap)
                .build();

    }

}
