package com.megamaker.codechallenge.user.domain.dto;

import com.megamaker.codechallenge.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ResponseUserRank {
    private final String nickname;
    private final Integer score;

    public static ResponseUserRank from(User user) {
        return new ResponseUserRank(user.getNickname(), user.getScore());
    }
}
