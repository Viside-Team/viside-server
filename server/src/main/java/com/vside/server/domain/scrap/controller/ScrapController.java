package com.vside.server.domain.scrap.controller;

import com.vside.server.domain.scrap.dto.ScrapContentResponse;
import com.vside.server.domain.scrap.dto.ScrapContentsDTO;
import com.vside.server.domain.scrap.dto.ScrapSuccessResponse;
import com.vside.server.domain.scrap.service.ScrapService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/scrap")
public class ScrapController {

    private final ScrapService scrapService;

    @GetMapping("/contents")
    @ApiOperation(value = "스크랩 컨텐츠 리스트 표출")
    public ResponseEntity<ScrapContentResponse> getScrapContentsList(Principal principal){
        String userId = principal.getName();
        int scrapCount = scrapService.getScrapCount(userId);
        List<ScrapContentsDTO> scrapContents = null; // count == 0일 경우 contents는 null
        if (scrapCount > 0)
            scrapContents = scrapService.getScrapContentList(userId);

        return ResponseEntity.ok().body(
                ScrapContentResponse.builder()
                .count(scrapCount)
                .contents(scrapContents)
                .build());
    }

    @PostMapping("/{contentId}")
    @ApiOperation(value = "스크랩/스크랩 취소")
    public ResponseEntity<ScrapSuccessResponse> scrap(@PathVariable Long contentId, Principal principal){
        return ResponseEntity
                .ok()
                .body(scrapService.scrap(contentId, principal.getName()));
    }

}
