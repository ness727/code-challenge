package com.megamaker.codechallenge.controller;

import jakarta.servlet.http.Cookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/login-check")
    public ResponseEntity loginCheck(@CookieValue(value = "JSESSIONID", required = false) Cookie cookie) {
        if (cookie != null) return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

}
