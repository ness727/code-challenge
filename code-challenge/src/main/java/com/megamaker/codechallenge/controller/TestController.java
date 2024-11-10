package com.megamaker.codechallenge.controller;

import com.megamaker.codechallenge.domain.user.Role;
import com.megamaker.codechallenge.securityconfig.oauth2.CustomOAuth2User;
import com.megamaker.codechallenge.securityconfig.oauth2.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

//    @GetMapping("/test-login")
//    public ResponseEntity testLogin() {
//        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//        CustomOAuth2User newTestUser = new CustomOAuth2User(new TestResponse(), Role.USER.name());
//        Authentication testAuth = new UsernamePasswordAuthenticationToken(newTestUser, null);
//        securityContext.setAuthentication(testAuth);
//        SecurityContextHolder.setContext(securityContext);
//        return ResponseEntity.noContent().build();
//    }
}
