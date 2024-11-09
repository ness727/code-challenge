package com.megamaker.codechallenge.controller;

import com.megamaker.codechallenge.dto.user.RequestUserEdit;
import com.megamaker.codechallenge.dto.user.ResponseUser;
import com.megamaker.codechallenge.securityconfig.oauth2.CustomOAuth2User;
import com.megamaker.codechallenge.service.UserService;
import com.megamaker.codechallenge.service.exception.UserNotFoundException;
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

    @GetMapping
    public ResponseUser get(Authentication auth) {
        CustomOAuth2User oAuth2User = getCustomOAuth2User(auth);

        return userService.get(oAuth2User.getProviderId());
    }

    @PutMapping
    public ResponseEntity edit(Authentication auth, @RequestBody RequestUserEdit requestUserEdit) {
        CustomOAuth2User oAuth2User = getCustomOAuth2User(auth);

        userService.edit(oAuth2User.getProviderId(), requestUserEdit);
        return ResponseEntity.noContent().build();
    }

    private static CustomOAuth2User getCustomOAuth2User(Authentication auth) {
        if (auth == null) throw new UserNotFoundException();
        CustomOAuth2User oAuth2User = (CustomOAuth2User) auth.getPrincipal();
        return oAuth2User;
    }
}
