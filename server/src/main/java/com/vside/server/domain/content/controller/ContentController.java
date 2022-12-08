package com.vside.server.domain.content.controller;

import com.vside.server.domain.content.dto.ContentPageResponse;
import com.vside.server.domain.content.dto.ContentRequest;
import com.vside.server.domain.content.dto.ContentResponse;
import com.vside.server.domain.content.dto.KeywordRequest;
import com.vside.server.domain.content.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping("/addContent")
    @ApiOperation(value = "content Add", notes = "컨텐츠 자료 넣기")
    public ResponseEntity<Boolean> addContent( @RequestBody ContentRequest contentRequest){
        System.out.println(contentRequest.getIsBrightBg());
        return ResponseEntity.ok().body(contentService.addContent(contentRequest));
    }

    @PostMapping("/addCategory")
    @ApiOperation(value = "Category Add", notes = "카테고리 자료 넣기")
    public ResponseEntity<Boolean> addCategory( @RequestBody KeywordRequest keywordRequest){
        return ResponseEntity.ok().body(contentService.addCategory(keywordRequest));
    }

    @GetMapping("/homelist")
    @ApiOperation(value = "메인 홈 화면 컨텐츠 리스트 표출")
    public ResponseEntity<Map<String, List>> getContentsList(Principal principal){
        List<ContentResponse> responseList = contentService.getContentHomeList(principal.getName());
        Map<String, List> response = new HashMap<>();
        response.put("contents", responseList);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/content/{content_Id}")
    @ApiOperation(value = "컨텐츠 상세 페이지")
    public ResponseEntity<ContentPageResponse> getContent(@PathVariable Long content_Id, Principal principal){
        ContentPageResponse response = contentService.getContent(principal.getName(),content_Id);
        return ResponseEntity.ok().body(response);
    }

}
