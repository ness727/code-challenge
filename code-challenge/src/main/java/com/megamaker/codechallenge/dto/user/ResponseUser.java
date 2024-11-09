package com.megamaker.codechallenge.dto.user;

import com.megamaker.codechallenge.domain.user.Role;
import com.megamaker.codechallenge.securityconfig.Provider;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public ResponseUser(Provider provider, String providerId, String providerNickname,
                        String nickname, Integer score, Role role) {
        this.provider = provider;
        this.providerId = providerId;
        this.providerNickname = providerNickname;
        this.nickname = nickname;
        this.score = score;
        this.role = role;
        this.avatar = "https://github.com/" + providerNickname + ".png";
    }
}
