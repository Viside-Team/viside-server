package com.vside.server.domain.scrap.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapContentResponse {

    private int count;
    private List<ScrapContentsDTO> contents;

}
