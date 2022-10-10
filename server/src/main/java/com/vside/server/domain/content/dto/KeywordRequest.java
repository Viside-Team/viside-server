package com.vside.server.domain.content.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeywordRequest {

    @NotNull
    @ApiModelProperty(required = true, example = "카테코리")
    private String Category;

    @NotNull
    @ApiModelProperty(required = true, example = "키워드")
    private List Keyword;


}
