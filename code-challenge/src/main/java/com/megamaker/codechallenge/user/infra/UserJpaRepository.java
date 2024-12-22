package com.megamaker.codechallenge.user.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByProviderId(String providerId);

//    @Query("select u from UserEntity u left join fetch u.userProblemEntityList where u.providerId like :providerId")
//    Optional<UserEntity> findByProviderIdWithUserProblem(@Param("providerId") String providerId);

    // 점수 상위 유저 n명 조회
    @Query(value = "select * from users u where u.role = 2 order by u.score desc limit :n", nativeQuery = true)
    List<UserEntity> findTopNByOrderByScoreDesc(@Param("n") int n);

    @Query(value = "select up.answer from users u inner join user_problems up on u.id = up.user_id where u.provider_id = :providerId and up.problem_id = :problemId limit 1", nativeQuery = true)
    Optional<String> findAnswerByProviderIdAndProblemId(String providerId, Long problemId);
}
