package com.vside.server.domain.auth.service;

import static com.vside.server.exception.ErrorMessage.USER_ALREADY_EXISTS;

import com.vside.server.domain.auth.dto.WithdrawalRequestDto;
import com.vside.server.domain.auth.dto.WithdrawalResponseDtoCode;
import com.vside.server.domain.common.LoginType;
import com.vside.server.domain.user.Entity.User;
import com.vside.server.domain.user.dao.UserRepository;
import com.vside.server.domain.user.dto.JoinRequest;
import com.vside.server.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService{

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public Long join(JoinRequest joinRequest) {
        if (exists(joinRequest.getLoginType(), joinRequest.getSnsId())) {
            throw new IllegalStateException(USER_ALREADY_EXISTS.getMessage());
        }
        User user = userRepository.save(joinRequest.toEntity());
        return user.getUserId();
    }

    @Transactional(readOnly = true)
    public boolean exists(LoginType provider, String snsId) {
        return userRepository.countByLoginTypeAndSnsId(provider, snsId) > 0;
    }

    @Transactional(readOnly = true)
    public User getExistingUser(String snsId) {
        return userRepository.findOneBySnsId(snsId);
    }

    @Transactional
    public WithdrawalResponseDtoCode delExistingUser(WithdrawalRequestDto dto) {
        if (userRepository.findBySnsId(dto.getSnsId()).isEmpty()){
            return WithdrawalResponseDtoCode.INVALID_USERID;
        }
        userRepository.delete(userRepository.findBySnsId(dto.getSnsId()).get());
        return WithdrawalResponseDtoCode.SUCCESS;
    }

    @Transactional
    public void logout_t(String jwt){
        tokenProvider.logout_token(jwt);
    }
}
