package com.megamaker.codechallenge.userproblem.domain;

import java.util.List;
import java.util.Optional;

public interface UserProblemRepository {
    Optional<UserProblem> findByUserIdAndProblemId(Long userId, Long problemId);

    List<UserProblem> findByUserId(Long userId);

    void save(UserProblem userProblem);
}
