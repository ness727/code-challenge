package com.megamaker.codechallenge.repository;

import com.megamaker.codechallenge.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderId(String providerId);

    // 점수 상위 유저 3명 조회
    List<User> findTop3ByOrderByScoreDesc();
}
