package com.megamaker.codechallenge.badge.domain;

import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, BadgeEnum> {
}
