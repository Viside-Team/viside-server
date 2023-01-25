package com.vside.server.domain.content.service;

import com.vside.server.domain.content.Entity.Content;
import com.vside.server.domain.content.Entity.ContentKeyword;
import com.vside.server.domain.content.dao.ContentKeywordRepository;
import com.vside.server.domain.content.dao.ContentRepository;
import com.vside.server.domain.content.dto.ContentPageResponse;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentService {
    private static final String ANONYMOUS_USER = "-1";

    private final ContentRepository contentRepository;
    private final ContentKeywordRepository contentKeywordReporitory;
    private final CategoryRepository categoryRepository;
    private final KeywordRepository keywordRepository;
    private final ScrapRepository scrapRepository;

    @Transactional(readOnly = false)
    public Boolean addContent(ContentRequest contentRequest){
        Content content  = Content.builder()
                .contentTitle(contentRequest.getContentTitle())
                .contentLink(contentRequest.getContentLink())
                .contentMainKeyword(keywordRepository.findByKeyword(contentRequest.getContentMainKeyword()))
                .imgLink(contentRequest.getImgLink())
                .coverImgUrl(contentRequest.getCoverImgLink())
                .lighterColor(contentRequest.getLighterColor())
                .darkerColor(contentRequest.getDarkerColor())
                .uploadDate(LocalDateTime.now())
                .build();
        content.setBrightBg(contentRequest.getIsBrightBg());
        System.out.println(content.isBrightBg());
        contentRepository.save(content);
//        contentRequest.getKeywords().add(contentRequest.getContentMainKeyword());
        for(String keywordName : contentRequest.getKeywords()){
            Keyword keyword = keywordRepository.findByKeyword(keywordName);
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
        Category category ;
        if(categoryRepository.existsCategoryByCategory(keywordRequest.getCategory())){
            category = categoryRepository.findByCategory(keywordRequest.getCategory());
        }
        else {
            category = Category.builder().category(keywordRequest.getCategory()).build();
        }
        for(Object keywordName : keywordRequest.getKeyword()){

            if(keywordRepository.existsKeywordByKeyword((String) keywordName)) {
                Keyword keyword = keywordRepository.findByKeyword((String)keywordName);
                category.addCategory(keyword);
                categoryRepository.save(category);
            }
            else {
                Keyword keyword = new Keyword((String) keywordName);
                keywordRepository.save(keyword);
                category.addCategory(keyword);
                categoryRepository.save(category);
            }
        }

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

    @Transactional(readOnly = true)
    public ContentPageResponse getContent(String userId, Long contentId) {
        Content content = contentRepository.findByContentId(contentId);
        return content.entityToContentPageDTO(
                content.getContentId(),
                content.getContentTitle(),
                content.getContentMainKeyword().getKeyword(),
                content.getImgLink(),
                content.getContentKeywords(),
                content.getContentLink(),
                content.isBrightBg(),
                scrapRepository.existsByContentContentIdAndUserUserId(content.getContentId(), Long.parseLong(userId)),
                content.getUploadDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }
}
