package com.megamaker.admin.securityconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final String[] permitPathArr = {"/login", "/img/**", "/css/**", "/js/**", "/assets/**",
            "/error", "/actuator/**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((auth) -> auth
                                // 로그인 관련
                                .requestMatchers(permitPathArr).permitAll()
                                //.anyRequest().authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(formConfig -> formConfig
                        //.loginPage("/login")
                        .failureForwardUrl("/")
                        .successForwardUrl("/")
                        .permitAll()
                )
                .logout(logoutConfig -> logoutConfig
                        //.logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )
                .build();
    }
}
