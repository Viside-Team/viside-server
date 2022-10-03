package com.vside.server.domain.keyword.service;

import com.vside.server.domain.content.Entity.ContentKeyword;
import com.vside.server.domain.content.dao.ContentKeywordReporitory;
import com.vside.server.domain.content.dao.ContentRepository;
import com.vside.server.domain.keyword.Entity.Category;
import com.vside.server.domain.keyword.Entity.Keyword;
import com.vside.server.domain.keyword.dao.CategoryRepository;
import com.vside.server.domain.keyword.dao.KeywordRepository;
import com.vside.server.domain.keyword.dto.KeywordRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;
    private final CategoryRepository categoryRepository;
    private final ContentKeywordReporitory contentKeywordReporitory;
    private final ContentRepository contentRepository;
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
    public List<Map<String,Object>> getcontentList(KeywordRequest keywordRequest){
        Set<Map<String,Object>> contentSet = new HashSet<>();
        for(int i=0;i<keywordRequest.getKeywordList().size();i++){
            String keyword = (String) keywordRequest.getKeywordList().get(i);
            for (int j=0;j<contentKeywordReporitory.findAllByKeyword(keywordRepository.findByKeyword(keyword)).size();j++){
                Map<String,Object> contentInfo = new HashMap<>();
                ContentKeyword contentKeyword = contentKeywordReporitory.findAllByKeyword(keywordRepository.findByKeyword(keyword)).get(j);
                String contentTitle = contentKeyword.getContent().getContentTitle();
                String contentImg = contentKeyword.getContent().getContentLink();
                List<String > contentKeywords = new ArrayList<>();
                for (int x=0;x<contentKeywordReporitory.findAllByContent(contentRepository.findByContentTitle(contentTitle)).size();x++) {
                    contentKeywords.add(contentKeywordReporitory.findAllByContent(contentRepository.findByContentTitle(contentTitle)).get(x).getKeyword().getKeyword());
                }
                contentInfo.put("title",contentTitle);
                contentInfo.put("img",contentImg);
                contentInfo.put("keywords",contentKeywords);

                contentSet.add(contentInfo);
            }
        }
        List<Map<String,Object>>contentList = new ArrayList<>(contentSet);

        return contentList;

    }
}
