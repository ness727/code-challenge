package com.megamaker.codechallenge.badge.infra;

import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeJpaRepository extends JpaRepository<BadgeEntity, BadgeEnum> {
}
