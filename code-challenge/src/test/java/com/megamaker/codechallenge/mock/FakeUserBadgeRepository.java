package com.megamaker.codechallenge.mock;

import com.megamaker.codechallenge.badge.userbadge.domain.UserBadge;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadgeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FakeUserBadgeRepository implements UserBadgeRepository {
    private final List<UserBadge> userBadgeList = new ArrayList<>();
    private long id;

    @Override
    public List<UserBadge> findByUserId(Long userId) {
        return userBadgeList.stream()
                .filter(userBadge -> userBadge.getUserId().equals(userId))
                .toList();
    }

    @Override
    public void saveAll(List<UserBadge> userBadgeList) {
        this.userBadgeList.addAll(
                userBadgeList.stream()
                        .map(userBadge -> new UserBadge(increaseIdAndGet(), userBadge.getUserId(),
                                userBadge.getBadge(),
                                userBadge.getCreatedAt() == null ? LocalDateTime.now() : userBadge.getCreatedAt())
                        )
                        .toList()
        );
    }

    private long increaseIdAndGet() {
        return ++id;
    }
}
