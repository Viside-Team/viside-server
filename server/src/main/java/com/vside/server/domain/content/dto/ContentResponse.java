package com.vside.server.domain.content.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentResponse {
    private Long contentId;
    private String title;
    private String imgUrl;
    private String mainKeyword;
    private List<String> keywords;
    private boolean isScrap;
}
