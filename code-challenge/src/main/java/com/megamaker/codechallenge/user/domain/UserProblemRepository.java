package com.megamaker.codechallenge.user.domain;

import com.megamaker.codechallenge.common.UserProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProblemRepository extends JpaRepository<UserProblem, Long> {
    Optional<UserProblem> findByUserIdAndProblemId(Long userId, Long problemId);
}
