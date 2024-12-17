package com.megamaker.codechallenge.badge.infra;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.badge.domain.BadgeRepository;
import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BadgeJpaRepositoryImpl implements BadgeRepository {
    private final BadgeJpaRepository badgeJpaRepository;

    @Override
    public Badge getReferenceById(BadgeEnum badgeEnum) {
        return BadgeEntity.toModel(badgeJpaRepository.getReferenceById(badgeEnum));
    }
}
