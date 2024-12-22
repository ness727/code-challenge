package com.megamaker.codechallenge.badge.userbadge.domain;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserBadge {
    private final Long id;
    private final Long userId;
    private final Badge badge;
    private final LocalDateTime createdAt;
}
