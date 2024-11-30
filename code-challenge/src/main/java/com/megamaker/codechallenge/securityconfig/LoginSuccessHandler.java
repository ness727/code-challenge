package com.megamaker.codechallenge.securityconfig;

import com.megamaker.codechallenge.domain.login.TokenRepository;
import com.megamaker.codechallenge.securityconfig.oauth2.CustomOAuth2User;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final Environment environment;
    private final TokenRepository tokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        String providerId = ((CustomOAuth2User) authentication.getPrincipal()).getProviderId();

        Key secretKey = new SecretKeySpec(Base64.getEncoder().encode(environment.getProperty("token.secret").getBytes()),
                Jwts.SIG.HS256.key().build().getAlgorithm());

        String token = Jwts.builder()
                .subject(providerId)
                .expiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(secretKey)
                .compact();

        // 토큰 저장소에 저장
        // return 값은 저장된 key
        String key = tokenRepository.save(token);

        String redirectUri;
        String referer = request.getHeader("Referer");
        if (StringUtils.hasText(referer)
                && (referer.contains("localhost") || referer.contains("127.0.0.1"))) {
            redirectUri = environment.getProperty("dev.login.success-uri");
        } else {
            redirectUri = environment.getProperty("prod.login.success-uri");
        }
        response.sendRedirect(redirectUri + "?key=" + key);
    }
}
