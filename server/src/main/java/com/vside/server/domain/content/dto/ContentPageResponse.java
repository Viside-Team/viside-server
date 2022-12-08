package com.vside.server.domain.content.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentPageResponse {

    private Long contentId;
    private String title;
    private String contentImgUrl;
    private String mainKeyword;
    private List<String> keywords;
    private String contentLink;
    private boolean isScrap;
    private boolean isBrightBg;

}
