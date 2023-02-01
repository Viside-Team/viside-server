package com.vside.server.domain.scrap.service;

import static com.vside.server.exception.ErrorMessage.*;

import com.vside.server.domain.content.Entity.Content;
import com.vside.server.domain.content.dao.ContentRepository;
import com.vside.server.domain.scrap.Entity.Scrap;
import com.vside.server.domain.scrap.dao.ScrapRepository;
import com.vside.server.domain.scrap.dto.ScrapContentsDTO;
import com.vside.server.domain.scrap.dto.ScrapSuccessResponse;
import com.vside.server.domain.user.Entity.User;
import com.vside.server.domain.user.dao.UserRepository;
import com.vside.server.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public int getScrapCount(String userId) {
        return scrapRepository.countByUserUserId(Long.parseLong(userId));
    }

    @Transactional(readOnly = true)
    public List<ScrapContentsDTO> getScrapContentList(String userId) {
        List<Content> scrapContents = scrapRepository
                .findScrapContentsByUserId(Long.parseLong(userId));
        return scrapContents
                .stream()
                .map(c -> c.entityToScrapContentDTO(
                        c.getContentId(),
                        c.getContentTitle(),
                        c.getContentLink(),
                        c.getCoverImgUrl(),
                        c.getImgLink(),
                        c.getContentKeywords(),
                        true)
                )
                .collect(Collectors.toList());
    }

    @Transactional
    public ScrapSuccessResponse scrap(Long contentId, String strUserId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CONTENTS_REQUEST.getMessage()));
        User user = userRepository.findById(Long.parseLong(strUserId))
                .orElseThrow(() -> new IllegalArgumentException(INVALID_USER_REQUEST.getMessage()));

        // 해당 조합의 컨텐츠-유저 연결 관계가 없다면 스크랩 수행
        if (!exists(content, user)) {
            return postScrap(content, user);
        }

        return deleteScrap(content, user);
    }

    @Transactional
    private ScrapSuccessResponse postScrap(Content content, User user) {
        Scrap newScrap = new Scrap(content, user);
        return ScrapSuccessResponse.builder()
                .status(scrapRepository.save(newScrap).getScrapId() > 0)
                .message("스크랩 성공")
                .build();
    }

    @Transactional
    private ScrapSuccessResponse deleteScrap(Content content, User user) {
        scrapRepository.delete(scrapRepository.findByContentAndUser(content, user));
        return ScrapSuccessResponse.builder()
                .status(true)
                .message("스크랩 취소 성공")
                .build();
    }

    @Transactional(readOnly = true)
    private boolean exists(Content content, User user) {
        return scrapRepository.existsByContentAndUser(content, user);
    }
}
