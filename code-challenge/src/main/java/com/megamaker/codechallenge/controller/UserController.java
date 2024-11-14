package com.megamaker.codechallenge.controller;

import com.megamaker.codechallenge.dto.user.RequestUserEdit;
import com.megamaker.codechallenge.dto.user.ResponseUser;
import com.megamaker.codechallenge.repository.TokenRepository;
import com.megamaker.codechallenge.service.UserService;
import com.megamaker.codechallenge.service.exception.UserNotFoundException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;
    private final TokenRepository tokenRepository;

    @GetMapping
    public ResponseUser get(Authentication auth) {
        String providerId = authToProviderId(auth);

        return userService.get(providerId);
    }

    @PutMapping
    public ResponseEntity edit(Authentication auth, @RequestBody RequestUserEdit requestUserEdit) {
        String providerId = authToProviderId(auth);

        userService.edit(providerId, requestUserEdit);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/token")
    public String getToken(@RequestHeader(name = "Key") String key) {
        String token = tokenRepository.get(key);

        if (StringUtils.isEmpty(token)) throw new UserNotFoundException();
        return token;
    }

    private static String authToProviderId(Authentication auth) {
        if (auth == null) throw new UserNotFoundException();
        return (String) auth.getPrincipal();
    }
}
