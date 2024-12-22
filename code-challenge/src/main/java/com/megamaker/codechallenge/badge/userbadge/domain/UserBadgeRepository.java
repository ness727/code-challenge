package com.megamaker.codechallenge.badge.userbadge.domain;

import java.util.List;

public interface UserBadgeRepository {
    List<UserBadge> findByUserId(Long userId);

    void saveAll(List<UserBadge> userBadgeList);
}
