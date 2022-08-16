package com.vside.server.domain.auth;

import com.vside.server.domain.user.Entity.Gender;
import com.vside.server.domain.user.Entity.LoginType;

import java.util.Map;

public class KakaoUserInfo implements Oauth2UserInfo{

    private final Map<String, Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public LoginType getProvider() {
        return LoginType.KAKAO;
    }

    @Override
    public String getEmail() {
        return (String) getKakaoAccount().get("email");
    }

    @Override
    public String getNickName() {
        return (String) getProfile().get("nickname");
    }

    @Override
    public Gender getGender() {
        String gender = (String) getKakaoAccount().get("gender");
        if (gender.equals("male")) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }
    }

    @Override
    public String getAgeRange(){
        return (String) getKakaoAccount().get("age_range");
    }

    public Map<String, Object> getKakaoAccount(){
        return(Map<String, Object>) attributes.get("kakao_account");
    }

    public Map<String, Object> getProfile(){
        return (Map<String, Object>) getKakaoAccount().get("profile");
    }
}
