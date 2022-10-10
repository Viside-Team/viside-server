package com.vside.server.domain.keyword.dto;

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
public class KeywordRequest {
    @NotNull
    @ApiModelProperty(required = true,example = "검색한 키워드들")
    private List<String> keywordList;

}
