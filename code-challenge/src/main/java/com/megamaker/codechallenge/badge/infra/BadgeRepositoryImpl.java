package com.megamaker.codechallenge.badge.infra;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.badge.domain.BadgeRepository;
import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BadgeRepositoryImpl implements BadgeRepository {
    private final BadgeJpaRepository badgeJpaRepository;
    private final BadgeMapper badgeMapper;

    @Override
    public Badge getReferenceById(BadgeEnum badgeEnum) {
        return badgeMapper.toModel(badgeJpaRepository.getReferenceById(badgeEnum));
    }
}
