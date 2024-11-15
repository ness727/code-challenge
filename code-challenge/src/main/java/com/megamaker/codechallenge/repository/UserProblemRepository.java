package com.megamaker.codechallenge.repository;

import com.megamaker.codechallenge.domain.entity.UserProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProblemRepository extends JpaRepository<UserProblem, Long> {
    Optional<UserProblem> findByUserIdAndProblemId(Long userId, Long problemId);
}
