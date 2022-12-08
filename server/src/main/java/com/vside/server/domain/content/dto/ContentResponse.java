package com.vside.server.domain.content.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentResponse {
    private Long contentId;
    private String title;
    private String coverImgUrl;
    private String mainKeyword;
    private List<String> keywords;
    private boolean isScrap;
    private String lighterColor;
    private String darkerColor;
}
