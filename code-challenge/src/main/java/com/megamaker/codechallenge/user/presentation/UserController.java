package com.megamaker.codechallenge.user.presentation;

import com.megamaker.codechallenge.user.domain.dto.RequestUserEdit;
import com.megamaker.codechallenge.user.domain.dto.ResponseUser;
import com.megamaker.codechallenge.user.domain.dto.ResponseUserRank;
import com.megamaker.codechallenge.user.domain.TokenRepository;
import com.megamaker.codechallenge.user.application.UserService;
import com.megamaker.codechallenge.problem.exception.UserNotFoundException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        ResponseUser responseUser = userService.get(providerId);
        // log.info("{}", responseUser);
        return responseUser;
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

    @GetMapping("/rank")
    public List<ResponseUserRank> getRank() {
        return userService.getRank();
    }

    private static String authToProviderId(Authentication auth) {
        if (auth == null) throw new UserNotFoundException();
        return (String) auth.getPrincipal();
    }
}
