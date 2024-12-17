package com.megamaker.codechallenge.badge.domain;

import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Badge {
    private final BadgeEnum id;
    private final String description;
    private final String imageUrl;
}
