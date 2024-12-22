package com.megamaker.codechallenge.badge.infra;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.badge.domain.BadgeRepository;
import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BadgeRepositoryImpl implements BadgeRepository {
    private final BadgeJpaRepository badgeJpaRepository;

    @Override
    public Badge getReferenceById(BadgeEnum badgeEnum) {
        return badgeJpaRepository.getReferenceById(badgeEnum).toModel();
    }

    @Override
    public Optional<Badge> findById(BadgeEnum badgeEnum) {
        return badgeJpaRepository.findById(badgeEnum).map(BadgeEntity::toModel);
    }
}
