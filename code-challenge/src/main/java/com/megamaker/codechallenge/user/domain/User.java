package com.megamaker.codechallenge.user.domain;

import com.megamaker.codechallenge.user.domain.vo.Provider;
import com.megamaker.codechallenge.user.domain.vo.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private final Long id;

    private final Provider provider;

    private final String nickname;

    private final Integer solveCount;

    private final Integer score;

    private final Role role;

//    private final List<UserBadge> userBadgeList;
//
//    private final List<UserProblem> userProblemList;

    public User addScoreAndSolveCount(Byte problemScore) {
        return User.builder()
                .id(id)
                .nickname(nickname)
                .provider(provider)
                .role(role)
                .score(score + problemScore)
                .solveCount(solveCount + 1)
//                .userBadgeList(userBadgeList)
//                .userProblemList(userProblemList)
                .build();
    }

    public User updateNickname(String newNickname) {
        return User.builder()
                .id(id)
                .nickname(newNickname)
                .provider(provider)
                .role(role)
                .score(score)
                .solveCount(solveCount)
//                .userBadgeList(userBadgeList)
//                .userProblemList(userProblemList)
                .build();
    }
}
