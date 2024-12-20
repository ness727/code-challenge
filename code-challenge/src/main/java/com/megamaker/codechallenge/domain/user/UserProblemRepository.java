package com.megamaker.codechallenge.domain.user;

import com.megamaker.codechallenge.domain.UserProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProblemRepository extends JpaRepository<UserProblem, Long> {
    Optional<UserProblem> findByUserIdAndProblemId(Long userId, Long problemId);
}
