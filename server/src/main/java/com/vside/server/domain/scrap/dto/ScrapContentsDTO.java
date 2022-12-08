package com.vside.server.domain.scrap.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapContentsDTO {

    private Long contentId;
    private String title;
    private String coverImgUrl;
    private List<String> keywords;
    private boolean isScrap;

}
