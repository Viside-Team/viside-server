package com.vside.server.domain.content.controller;

import com.vside.server.domain.content.dto.ContentRequest;
import com.vside.server.domain.content.dto.KeywordRequest;
import com.vside.server.domain.content.service.ContentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    @PostMapping("/addContent")
    @ApiOperation(value = "content Add", notes = "컨텐츠 자료 넣기")
    public ResponseEntity<Boolean> addContent( @RequestBody ContentRequest contentRequest){
        return ResponseEntity.ok().body(contentService.addContent(contentRequest));
    }

    @PostMapping("/addCategory")
    @ApiOperation(value = "Category Add", notes = "카테고리 자료 넣기")
    public ResponseEntity<Boolean> addCategory( @RequestBody KeywordRequest keywordRequest){
        System.out.println(keywordRequest.getKeyword());
        System.out.println(keywordRequest.getCategory());

        return ResponseEntity.ok().body(contentService.addCategory(keywordRequest));

    }

}
