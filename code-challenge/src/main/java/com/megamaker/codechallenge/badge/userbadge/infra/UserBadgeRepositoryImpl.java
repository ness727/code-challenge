package com.megamaker.codechallenge.badge.userbadge.infra;

import com.megamaker.codechallenge.badge.userbadge.domain.UserBadge;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserBadgeRepositoryImpl implements UserBadgeRepository {
    private final UserBadgeJpaRepository userBadgeJpaRepository;

    @Override
    public List<UserBadge> findByUserId(Long userId) {
        return userBadgeJpaRepository.findByUserId(userId).stream()
                .map(UserBadgeEntity::toModel)
                .toList();
    }

    @Override
    public void saveAll(List<UserBadge> userBadgeList) {
        userBadgeJpaRepository.saveAll(
                userBadgeList.stream()
                        .map(UserBadgeEntity::from)
                        .toList()
        );
    }
}
