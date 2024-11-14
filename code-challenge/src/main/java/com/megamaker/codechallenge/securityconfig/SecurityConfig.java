package com.megamaker.codechallenge.securityconfig;

import com.megamaker.codechallenge.repository.TokenRepository;
import com.megamaker.codechallenge.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final String[] permitPathArr = {"/", "/oauth2/**", "/login", "/error",
            "/user/token", "/problem/list"};

    private final CustomOAuth2UserService customOAuth2UserService;
    private final Environment environment;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Value("${custom.config.cors.allowedOrigins.front}")
    private String allowedFront;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(c ->
                        c.configurationSource(new CustomCorsConfig(allowedFront))
                )
                .sessionManagement(SessionManagementConfigurer::disable)
                .addFilterAfter(new LoginCheckFilter(permitPathArr, userRepository, environment), UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .oauth2Login((oauth2) ->
                                oauth2.userInfoEndpoint((endpointConfig) ->
                                                endpointConfig.userService(customOAuth2UserService)
                                        )
                                        .successHandler(new LoginSuccessHandler(environment, tokenRepository))
                        //.defaultSuccessUrl(loginSuccessUri, true)
                )
                .logout((logoutConfig) -> logoutConfig
                        .logoutUrl("/logout")
                        .logoutSuccessUrl(allowedFront)
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )
                .authorizeHttpRequests((auth) -> auth
                                // 로그인 관련
                                .requestMatchers(permitPathArr).permitAll()
                                .anyRequest().authenticated()
                        //.anyRequest().permitAll()
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
