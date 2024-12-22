package com.megamaker.codechallenge.userproblem.domain;

import com.megamaker.codechallenge.problem.domain.Problem;
import com.megamaker.codechallenge.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProblem {
    private final Long id;
    private final Long userId;
    private final Long problemId;
    private final String answer;

    public UserProblem updateAnswer(String newAnswer) {
        return new UserProblem(id, userId, problemId, newAnswer);
    }

}
