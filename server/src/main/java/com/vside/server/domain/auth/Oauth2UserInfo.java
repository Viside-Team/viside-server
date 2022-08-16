package com.vside.server.domain.auth;

import com.vside.server.domain.user.Entity.Gender;
import com.vside.server.domain.user.Entity.LoginType;

public interface Oauth2UserInfo {
    public String getProviderId();
    public LoginType getProvider();
    public String getEmail();
    public String getNickName();
    public Gender getGender();
    public String getAgeRange();
}
