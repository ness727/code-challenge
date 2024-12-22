package com.megamaker.codechallenge.userproblem.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProblemJpaRepository extends JpaRepository<UserProblemEntity, Long> {
    Optional<UserProblemEntity> findByUserIdAndProblemId(Long userId, Long problemId);

    List<UserProblemEntity> findByUserId(Long userId);
}
