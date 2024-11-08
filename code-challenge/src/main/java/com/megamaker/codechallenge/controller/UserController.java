package com.megamaker.codechallenge.controller;

import com.megamaker.codechallenge.dto.user.RequestUserEdit;
import com.megamaker.codechallenge.securityconfig.oauth2.CustomOAuth2User;
import com.megamaker.codechallenge.service.UserService;
import com.megamaker.codechallenge.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

//    @GetMapping
//    public ResponseUser get(Auth auth) {
//
//    }

    @PutMapping
    public ResponseEntity edit(Authentication auth, @RequestBody RequestUserEdit requestUserEdit) {
        if (auth == null) throw new UserNotFoundException();
        CustomOAuth2User oAuth2User = (CustomOAuth2User) auth.getPrincipal();

        userService.edit(oAuth2User.getProviderId(), requestUserEdit);
        return ResponseEntity.noContent().build();
    }
}
