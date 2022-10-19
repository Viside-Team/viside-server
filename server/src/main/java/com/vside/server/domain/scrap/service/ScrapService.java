package com.vside.server.domain.scrap.service;

import com.vside.server.domain.content.Entity.Content;
import com.vside.server.domain.scrap.dao.ScrapRepository;
import com.vside.server.domain.scrap.dto.ScrapContentsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;

    @Transactional(readOnly = true)
    public int getScrapCount(String userId){
        return scrapRepository.countByUserUserId(Long.parseLong(userId));
    }

    @Transactional(readOnly = true)
    public List<ScrapContentsDTO> getScrapContentList(String userId){
        List<Content> scrapContents =  scrapRepository.findScrapContentsByUserId(Long.parseLong(userId));
        return scrapContents
                .stream()
                .map(c -> c.entityToScrapContentDTO(
                        c.getContentId(),
                        c.getContentTitle(),
                        c.getImgLink(),
                        c.getContentKeywords(),
                        true)
                )
                .collect(Collectors.toList());
    }
}
