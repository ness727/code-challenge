package com.megamaker.codechallenge.badge.infra;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BadgeJpaRepository extends JpaRepository<BadgeEntity, BadgeEnum> {
    Optional<BadgeEntity> findById(BadgeEnum badgeEnum);
}
