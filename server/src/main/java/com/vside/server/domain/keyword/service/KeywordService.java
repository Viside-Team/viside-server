package com.vside.server.domain.keyword.service;

import com.vside.server.domain.content.Entity.Content;
import com.vside.server.domain.content.Entity.ContentKeyword;
import com.vside.server.domain.content.dao.ContentKeywordRepository;
import com.vside.server.domain.content.dao.ContentRepository;
import com.vside.server.domain.content.dto.ContentResponse;
import com.vside.server.domain.keyword.Entity.Category;
import com.vside.server.domain.keyword.Entity.Keyword;
import com.vside.server.domain.keyword.dao.CategoryRepository;
import com.vside.server.domain.keyword.dao.KeywordRepository;
import com.vside.server.domain.keyword.dto.KeywordRequest;
import com.vside.server.domain.scrap.dao.ScrapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordService {
    private static final String ANONYMOUS_USER = "-1";

    private final KeywordRepository keywordRepository;
    private final CategoryRepository categoryRepository;
    private final ContentKeywordRepository contentKeywordReporitory;
    private final ContentRepository contentRepository;
    private final ScrapRepository scrapRepository;
    @Transactional(readOnly = true)
    public List<Map<String,List<String>>> getCategoryList(){
        List<Map<String ,List<String>>> categoryKeywordList = new ArrayList<>();
        List<Category>categoryList =  categoryRepository.findAll();
        for(int i=0;i<categoryList.size();i++){
            String category = categoryList.get(i).getCategory();
            List<String > categoryArray = new ArrayList<>();
            categoryArray.add(category);

            List<Keyword>keywordList = categoryRepository.findByCategory(category).getKeywords();
            List<String > keywordArray = new ArrayList<>();
            for (int j=0;j<keywordList.size();j++){
                keywordArray.add(keywordList.get(j).getKeyword());
            }
            Map<String ,List<String>> map = new HashMap<>();
            map.put("category",categoryArray);
            map.put("keywords",keywordArray);
            categoryKeywordList.add(map);
        }
        return categoryKeywordList;
    }
    @Transactional(readOnly = true)
    public List<ContentResponse> getcontentList(KeywordRequest keywordRequest, String userId){
//        Set<Content> contentSet = new HashSet<>();
        List<Content> contentList = new ArrayList<>();
        if(keywordRequest.getKeywordList().size()==0){
            List<Keyword> keywordList = keywordRepository.findAll();
            for(int keywordIndex =0;keywordIndex<keywordList.size();keywordIndex++){
                keywordRequest.getKeywordList().add(keywordList.get(keywordIndex).getKeyword());
            }
        }
        for(int i=0;i<keywordRequest.getKeywordList().size();i++){
            String keywordName = (String) keywordRequest.getKeywordList().get(i);
            Keyword keyword = keywordRepository.findByKeyword(keywordName);
            List<Content> contents = contentRepository.findByContentKeywordsIn(contentKeywordReporitory.findByKeyword(keyword));
            contents.addAll(contentRepository.findByContentMainKeyword(keyword));
            contentList.addAll(contents);

        }

        contentList= contentList.stream().distinct().toList();
        return contentList
                .stream()
                .map(c -> c.entityToHomeContentDTO(
                                c.getContentId(),
                                c.getContentTitle(),
                                c.getContentLink(),
                                c.getContentMainKeyword().getKeyword(),
                                c.getCoverImgUrl(),
                                c.getImgLink(),
                                c.getContentKeywords(),
                                c.getLighterColor(),
                                c.getDarkerColor(),
                                !userId.equals(ANONYMOUS_USER) && scrapRepository
                                        .existsByContentContentIdAndUserUserId(c.getContentId(), Long.parseLong(userId))
                        )
                )
                .collect(Collectors.toList());

    }
}
