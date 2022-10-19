package com.vside.server.domain.content.service;

import com.vside.server.domain.content.Entity.Content;
import com.vside.server.domain.content.Entity.ContentKeyword;
import com.vside.server.domain.content.dao.ContentKeywordRepository;
import com.vside.server.domain.content.dao.ContentRepository;
import com.vside.server.domain.content.dto.ContentRequest;
import com.vside.server.domain.content.dto.ContentResponse;
import com.vside.server.domain.content.dto.KeywordRequest;
import com.vside.server.domain.keyword.Entity.Category;
import com.vside.server.domain.keyword.Entity.Keyword;
import com.vside.server.domain.keyword.dao.CategoryRepository;
import com.vside.server.domain.keyword.dao.KeywordRepository;
import com.vside.server.domain.scrap.dao.ScrapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final ContentKeywordRepository contentKeywordReporitory;
    private final CategoryRepository categoryRepository;
    private final KeywordRepository keywordRepository;
    private final ScrapRepository scrapRepository;

    @Transactional(readOnly = false)
    public Boolean addContent(ContentRequest contentRequest){
        Content content  = Content.builder()
                .contentTitle(contentRequest.getContentName())
                .contentLink(contentRequest.getContentLink())
                .contentMainKeyword(contentRequest.getMainKeyword())
                .contentBody(contentRequest.getContentBody())
                .build();
        contentRepository.save(content);
        contentRequest.getKeywords().add(contentRequest.getMainKeyword());
        for(Object keywordName : contentRequest.getKeywords()){
            Keyword keyword = keywordRepository.findByKeyword((String) keywordName);
            System.out.println(keyword.getKeyword());
            ContentKeyword contentKeyword= new ContentKeyword();
            contentKeyword.setKeyword(keyword);
            contentKeyword.setContent(content);
            contentKeywordReporitory.save(contentKeyword);
        }
        return true;
    }

    @Transactional(readOnly = false)
    public Boolean addCategory(KeywordRequest keywordRequest){
        Category category = Category.builder().category(keywordRequest.getCategory()).build();
        for(Object keywordName : keywordRequest.getKeyword()){
            Keyword keyword = new Keyword((String) keywordName);
            keywordRepository.save(keyword);
            category.addCategory(keyword);
        }
        categoryRepository.save(category);

        return true;

    }

    @Transactional(readOnly = true)
    public List<ContentResponse> getContentHomeList(String userId) {
        List<Content> contentList = contentRepository.findAll();
        return contentList
                .stream()
                .map(c -> c.entityToHomeContentDTO(
                        c.getContentId(),
                        c.getContentTitle(),
                        c.getContentMainKeyword(),
                        c.getImgLink(),
                        c.getContentKeywords(),
                        scrapRepository.existsByContentContentIdAndUserUserId(c.getContentId(), Long.parseLong(userId))
                     )
                )
                .collect(Collectors.toList());
    }
}
