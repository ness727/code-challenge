package com.megamaker.codechallenge.domain.badge;

import com.megamaker.codechallenge.domain.badge.vo.BadgeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, BadgeEnum> {
}
