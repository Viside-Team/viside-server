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
    private String contentName;

    @NotNull
    @ApiModelProperty(required = true, example = "메인 키워드")
    private String mainKeyword;

    @NotNull
    @ApiModelProperty(required = true, example = "키워드들")
    private List<String> keywords;

    @NotNull
    @ApiModelProperty(required = true, example = "글내용")
    private String contentBody;

    @NotNull
    @ApiModelProperty(required = true, example = "이미지")
    private String contentLink;

}