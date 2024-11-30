package com.megamaker.codechallenge.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderProviderId(String providerId);

    // 점수 상위 유저 3명 조회
    List<User> findTop3ByOrderByScoreDesc();

    // 점수 상위 유저 10명 조회
    List<User> findTop10ByOrderByScoreDesc();

    @Query(value = "select up.answer from users u inner join user_problems up on u.id = up.user_id where u.provider_id = :providerId and up.problem_id = :problemId limit 1", nativeQuery = true)
    Optional<String> findAnswerByProviderIdAndProblemId(String providerId, Long problemId);
}
