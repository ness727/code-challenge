package com.megamaker.codechallenge.securityconfig;

import com.megamaker.codechallenge.service.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Value("${custom.config.cors.allowedOrigins.front}")
    private String allowedFront;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(c ->
                        c.configurationSource(new CustomCorsConfig(allowedFront))
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .oauth2Login((oauth2) ->
                        oauth2.userInfoEndpoint((endpointConfig) ->
                                endpointConfig.userService(customOAuth2UserService)
                        )
                        .defaultSuccessUrl(allowedFront, true)
                )
                .authorizeHttpRequests((auth) -> auth
                                // 로그인 관련
                                .requestMatchers("/", "/oauth2/**", "/login", "/error").permitAll()
                                // 문제 리스트 보기만 허용
                                .requestMatchers("/problem/list/**").permitAll()
                                //.anyRequest().authenticated()
                        .anyRequest().permitAll()
                )
                
                // 로그인하지 않았으면 401 응답
                .exceptionHandling(exceptionConfig ->
                        exceptionConfig.authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                        )
                )
                
                .build();
    }
}
