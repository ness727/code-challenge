package com.megamaker.codechallenge.dto.user;

import com.megamaker.codechallenge.domain.entity.UserBadge;
import com.megamaker.codechallenge.domain.user.Role;
import com.megamaker.codechallenge.mapper.BadgeMapper;
import com.megamaker.codechallenge.securityconfig.Provider;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseUser {
    private Provider provider;

    private String providerId;

    private String providerNickname;

    private String nickname;

    private String avatar;

    private Integer score;

    private Role role;

    private List<ResponseBadge> userBadgeList;

    public ResponseUser(Provider provider, String providerId, String providerNickname,
                        String nickname, Integer score, Role role, List<ResponseBadge> userBadgeList) {
        this.provider = provider;
        this.providerId = providerId;
        this.providerNickname = providerNickname;
        this.nickname = nickname;
        this.score = score;
        this.role = role;
        this.avatar = "https://github.com/" + providerNickname + ".png";
        this.userBadgeList = userBadgeList;
    }
}
