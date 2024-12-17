package com.megamaker.codechallenge.badge.domain;

import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;

public interface BadgeRepository {
    Badge getReferenceById(BadgeEnum badgeEnum);
}
