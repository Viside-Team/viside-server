package com.vside.server.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vside.server.domain.auth.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;


@Slf4j
@RequiredArgsConstructor
public class TokenHandlerFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        }
        catch (ExpiredJwtException e1){
            try{
                sendNewTokenResponse(request.getHeader("RefreshToken"), response, e1.getClaims());
            }
            catch (IllegalStateException e2){
                sendErrorResponse(response, e2.getMessage());
            }
        }

    }

    private void sendNewTokenResponse(final String refreshToken, HttpServletResponse response, Claims claims) throws IOException {
        final String accessToken = tokenService.handleToken(refreshToken, claims);

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");

        Map<String, String> tokenResponse = new HashMap<>();
        tokenResponse.put("accessToken", "Bearer " + accessToken);

        new ObjectMapper().writeValue(response.getWriter(), tokenResponse);
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", message);

        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
    }
}
