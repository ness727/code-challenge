package com.megamaker.codechallenge.config;

import com.megamaker.codechallenge.service.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final MessageSource messageSource;

//    @Bean
//    public WebSecurityCustomizer configure() {
//        return web -> web.ignoring()
//                .requestMatchers("/img/**", "/css/**", "/js/**", "/assets/**",
//                        "/error", "/actuator/**");  // 정적 자원은 필터 무시
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)  // 테스트 환경에서만 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .oauth2Login((oauth2) ->
                        oauth2.userInfoEndpoint((endpointConfig) ->
                                endpointConfig.userService(customOAuth2UserService)
                        )
                        .defaultSuccessUrl("http://localhost:3000", true)
                )
                .authorizeHttpRequests((auth) -> auth
                                // 로그인 관련
                                .requestMatchers("/", "/oauth2/**", "/login").permitAll()
                                // 문제 리스트 보기만 허용
                                .requestMatchers("/problem/list/**").permitAll()
                                .anyRequest().authenticated()
                        //.anyRequest().permitAll()\
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
