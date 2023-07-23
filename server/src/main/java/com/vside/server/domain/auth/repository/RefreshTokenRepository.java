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
        valueOperations.set("RT:" + authKey, refreshToken, 365, TimeUnit.DAYS);
    }

    public Boolean hasToken(String userId) {
        return redisTemplate.hasKey("RT:" + userId);
    }

    public boolean tokenEquals(String userId, String token) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String storedToken = valueOperations.get(userId);
        return storedToken != null && storedToken.equals(token);
    }
}
