package com.megamaker.codechallenge.user.domain.dto;

import com.megamaker.codechallenge.user.domain.User;
import com.megamaker.codechallenge.user.domain.vo.Provider;
import com.megamaker.codechallenge.user.domain.vo.Role;
import com.megamaker.codechallenge.badge.userbadge.domain.dto.ResponseUserBadge;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseUser {
    private final Provider provider;

    private final String nickname;

    private final String avatar;

    private final Integer solveCount;

    private final Integer score;

    private final Role role;

    private final List<ResponseUserBadge> userBadgeList;

    @Builder
    public ResponseUser(Provider provider, String nickname, Integer solveCount, Integer score,
                        Role role, List<ResponseUserBadge> userBadgeList) {
        this.provider = provider;
        this.nickname = nickname;
        this.solveCount = solveCount;
        this.score = score;
        this.role = role;
        this.avatar = "https://github.com/" + provider.getProviderNickname() + ".png";
        this.userBadgeList = userBadgeList;
    }

    public static ResponseUser from(User user, List<ResponseUserBadge> responseUserBadgeList) {
        return ResponseUser.builder()
                .provider(user.getProvider())
                .nickname(user.getNickname())
                .solveCount(user.getSolveCount())
                .score(user.getScore())
                .role(user.getRole())
                .userBadgeList(responseUserBadgeList)
                .build();
    }
}
