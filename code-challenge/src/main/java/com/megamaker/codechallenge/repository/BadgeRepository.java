package com.megamaker.codechallenge.repository;

import com.megamaker.codechallenge.domain.user.BadgeEnum;
import com.megamaker.codechallenge.domain.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, BadgeEnum> {
}
