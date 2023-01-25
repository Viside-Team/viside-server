package com.vside.server.domain.keyword.controller;

import com.vside.server.domain.content.dto.ContentResponse;
import com.vside.server.domain.keyword.dto.KeywordRequest;
import com.vside.server.domain.keyword.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class KeywordController {

    private final KeywordService keywordService;
    private static final String ANONYMOUS_USER = "-1";

    @GetMapping("/keywords")
    public ResponseEntity<Map<String,List>> getKeywordList(){
        List<Map<String ,List<String>>>categoryList =keywordService.getCategoryList();
        Map<String, List> response = new HashMap<>();
        response.put("categories",categoryList);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/search")
    public ResponseEntity<Map<String,List>> getSearchedContents(@RequestBody KeywordRequest keywordRequest,Principal principal){
        System.out.println(keywordRequest.getKeywordList());
        String userId = (principal == null) ? ANONYMOUS_USER : principal.getName();

        List<ContentResponse>contentList = keywordService.getcontentList(keywordRequest,userId);
        Map<String, List> response = new HashMap<>();
        response.put("contents",contentList);

        return ResponseEntity.ok().body(response);
    }
}
