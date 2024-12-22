package com.megamaker.codechallenge.badge.userbadge.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBadgeJpaRepository extends JpaRepository<UserBadgeEntity, Long> {
    List<UserBadgeEntity> findByUserId(Long userId);

}
