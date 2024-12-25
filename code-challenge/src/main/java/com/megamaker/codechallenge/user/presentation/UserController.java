package com.megamaker.codechallenge.user.presentation;

import com.megamaker.codechallenge.badge.application.BadgeService;
import com.megamaker.codechallenge.badge.userbadge.application.UserBadgeService;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadge;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadgeRepository;
import com.megamaker.codechallenge.badge.userbadge.domain.dto.ResponseUserBadge;
import com.megamaker.codechallenge.user.domain.User;
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
    private final UserBadgeService userBadgeService;
    private final TokenRepository tokenRepository;

    @GetMapping
    public ResponseUser get(Authentication auth) {
        String providerId = authToProviderId(auth);

        User foundUser = userService.get(providerId);
        List<UserBadge> userBadgeList = userBadgeService.getUserBadgeList(foundUser.getId());

        return ResponseUser.from(foundUser,
                userBadgeList.stream()
                .map(ResponseUserBadge::from)
                .toList()
        );
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
        return userService.getRank().stream()
                .map(user -> {
                    if (user.getNickname() == null) {  // 닉네임 설정 안 되어 있을 때
                        return new ResponseUserRank(
                                user.getProvider().getProviderNickname() + "(GitHub)", user.getScore()
                        );
                    } else return ResponseUserRank.from(user);
                })
                .toList();
    }

    private static String authToProviderId(Authentication auth) {
        if (auth == null) throw new UserNotFoundException();
        return (String) auth.getPrincipal();
    }
}
