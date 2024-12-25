package com.megamaker.codechallenge.badge.userbadge.application;

import com.megamaker.codechallenge.badge.userbadge.domain.UserBadge;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserBadgeService {
    private final UserBadgeRepository userBadgeRepository;

    public List<UserBadge> getUserBadgeList(Long userId) {
        return userBadgeRepository.findByUserId(userId);
    }
}
