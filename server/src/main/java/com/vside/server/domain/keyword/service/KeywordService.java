package com.vside.server.domain.keyword.service;

import com.vside.server.domain.content.Entity.ContentKeyword;
import com.vside.server.domain.content.dao.ContentKeywordRepository;
import com.vside.server.domain.content.dao.ContentRepository;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordService {
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
    public List<Map<String,Object>> getcontentList(KeywordRequest keywordRequest,String userId){
        Set<Map<String,Object>> contentSet = new HashSet<>();
        if(keywordRequest.getKeywordList().size()==0){
            List<Keyword> keywordList = keywordRepository.findAll();
            for(int keywordIndex =0;keywordIndex<keywordList.size();keywordIndex++){
                keywordRequest.getKeywordList().add(keywordList.get(keywordIndex).getKeyword());
            }
        }
        System.out.println(keywordRequest.getKeywordList());
        for(int i=0;i<keywordRequest.getKeywordList().size();i++){
            String keyword = (String) keywordRequest.getKeywordList().get(i);
            for (int j=0;j<contentKeywordReporitory.findAllByKeyword(keywordRepository.findByKeyword(keyword)).size();j++){
                Map<String,Object> contentInfo = new HashMap<>();
                ContentKeyword contentKeyword = contentKeywordReporitory.findAllByKeyword(keywordRepository.findByKeyword(keyword)).get(j);
                Long contentId = contentKeyword.getContent().getContentId();
                String contentTitle = contentKeyword.getContent().getContentTitle();
                String contentImg = contentKeyword.getContent().getCoverImgUrl();
                String contentMainKeyword = contentRepository.findByContentTitle(contentTitle).getContentMainKeyword();
                String contentBody = contentRepository.findByContentTitle(contentTitle).getContentBody();
                String contentLink = contentRepository.findByContentTitle(contentTitle).getContentLink();
                String contentLighterColor = contentRepository.findByContentTitle(contentTitle).getLighterColor();
                String contentDarkerColor = contentRepository.findByContentTitle(contentTitle).getDarkerColor();

                Set<String > contentKeywords = new HashSet<>();
                for (int x=0;x<contentKeywordReporitory.findAllByContent(contentRepository.findByContentTitle(contentTitle)).size();x++) {
                    contentKeywords.add(contentKeywordReporitory.findAllByContent(contentRepository.findByContentTitle(contentTitle)).get(x).getKeyword().getKeyword());
                }
                System.out.println(contentKeywords);
                contentKeywords.remove(contentMainKeyword);
                System.out.println(contentKeywords);
                contentInfo.put("contentId",contentId);
                contentInfo.put("title",contentTitle);
                contentInfo.put("coverImgUrl",contentImg);
                contentInfo.put("main_Keywords",contentMainKeyword);
                contentInfo.put("keywords",contentKeywords);
//                contentInfo.put("contentBody",contentBody);
                contentInfo.put("contentLink",contentLink);
                contentInfo.put("lighterColor",contentLighterColor);
                contentInfo.put("darkerColor",contentDarkerColor);
                contentInfo.put("scrap",scrapRepository.existsByContentContentIdAndUserUserId(contentId, Long.parseLong(userId)));

                contentSet.add(contentInfo);
            }
        }
        List<Map<String,Object>>contentList = new ArrayList<>(contentSet);

        return contentList;

    }
}
