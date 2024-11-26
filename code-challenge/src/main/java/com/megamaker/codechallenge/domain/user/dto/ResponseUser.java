package com.megamaker.codechallenge.domain.user.dto;

import com.megamaker.codechallenge.domain.user.vo.Provider;
import com.megamaker.codechallenge.domain.user.vo.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseUser {
    private Provider provider;

    private String nickname;

    private String avatar;

    private Integer score;

    private Role role;

    private List<ResponseBadge> userBadgeList;

    public ResponseUser(Provider provider, String nickname, Integer score,
                        Role role, List<ResponseBadge> userBadgeList) {
        this.provider = provider;
        this.nickname = nickname;
        this.score = score;
        this.role = role;
        this.avatar = "https://github.com/" + provider.getProviderNickname() + ".png";
        this.userBadgeList = userBadgeList;
    }
}
