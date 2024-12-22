package com.megamaker.codechallenge.badge.domain;

import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;

import java.util.Optional;

public interface BadgeRepository {
    Badge getReferenceById(BadgeEnum badgeEnum);

    Optional<Badge> findById(BadgeEnum badgeEnum);
}
