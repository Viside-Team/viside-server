package com.vside.server.domain.content.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentRequest {
    @NotNull
    @ApiModelProperty(required = true, example = "제목")
    private String contentTitle;

    @NotNull
    @ApiModelProperty(required = true, example = "메인 키워드")
    private String contentMainKeyword;

    @NotNull
    @ApiModelProperty(required = true, example = "키워드들")
    private List<String> keywords;



    @NotNull
    @ApiModelProperty(required = true, example = "컨텐츠 이미지")
    private String contentLink;

    @NotNull
    @ApiModelProperty(required = true, example = "이미지")
    private String imgLink;

    @NotNull
    @ApiModelProperty(required = true, example = "커버이미지")
    private String coverImgLink;

    @NotNull
    private String isBrightBg;

    @NotNull
    private String lighterColor;
    @NotNull
    private String darkerColor;
}
