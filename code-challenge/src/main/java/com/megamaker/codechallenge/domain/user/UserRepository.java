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

    @Query("select up.answer from User u inner join fetch UserProblem up on u.id = up.user.id where u.provider.providerId = :providerId and up.problem.id = :problemId")
    Optional<String> findAnswerByProviderIdAndProblemId(String providerId, Long problemId);
}
