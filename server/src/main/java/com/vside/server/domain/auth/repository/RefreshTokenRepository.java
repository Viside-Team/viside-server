package com.vside.server.domain.auth.repository;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenRepository {

    private final StringRedisTemplate redisTemplate;

    public void save(String authKey, String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        // TODO: 시간 수정 (현재는 테스트용)
        valueOperations.set("RT:" + authKey, refreshToken, 90, TimeUnit.SECONDS);
    }

}
