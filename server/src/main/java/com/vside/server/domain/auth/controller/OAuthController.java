package com.vside.server.domain.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vside.server.domain.auth.dto.LoginResponse;
import com.vside.server.domain.auth.util.AppleLoginUtil;
import com.vside.server.domain.user.service.OAuthService;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;
    private final AppleLoginUtil appleLoginUtil;
    /*
    authentication code로 첫 로그인 시 회원가입
    url: http://localhost:8080/login/oauth/kakao?code=********
     */
    @GetMapping("/login/oauth/{provider}")
    public ResponseEntity<LoginResponse> login(@PathVariable String provider, @RequestParam String code){
        LoginResponse loginResponse = oAuthService.login(provider, code);
        return ResponseEntity.ok().body(loginResponse);
    }
    // 애플 연동정보 조회
    @GetMapping("/login/oauth/apple/{provider}")
    public String oauth_apple(@RequestParam(value = "code") String code) throws Exception {

        String client_id = appleClentId;
        String client_secret = appleLoginUtil.createClientSecret(appleTeamId, appleClentId, appleKeyId, appleKeyPath, applehUrl);

        String reqUrl = appleAuthUrl + "/auth/token";

        Map<String, String> tokenRequest = new HashMap<>();

        tokenRequest.put("client_id", client_id);
        tokenRequest.put("client_secret", client_secret);
        tokenRequest.put("code", code);
        tokenRequest.put("grant_type", "authorization_code");

        String apiResponse = appleLoginUtil.doPost(reqUrl, tokenRequest);

        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject tokenResponse = objectMapper.readValue(apiResponse, JSONObject.class);

        // 애플 정보조회 성공
        if (tokenResponse.get("error") == null ) {

            JSONObject payload = appleLoginUtil.decodeFromIdToken(tokenResponse.getString("id_token"));
            //  회원 고유 식별자
            String appleUniqueNo = payload.getString("sub");

            /**

             TO DO : 리턴받은 appleUniqueNo 해당하는 회원정보 조회 후 로그인 처리 후 메인으로 이동

             */


            // 애플 정보조회 실패
        } else {
            throw new ErrorMessage("애플 정보조회에 실패했습니다.");
        }

    }
}
