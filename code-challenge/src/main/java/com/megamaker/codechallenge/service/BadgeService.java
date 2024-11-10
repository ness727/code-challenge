package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.domain.user.BadgeEnum;
import com.megamaker.codechallenge.entity.Badge;
import com.megamaker.codechallenge.entity.User;
import com.megamaker.codechallenge.entity.UserBadge;
import com.megamaker.codechallenge.repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;
    
    public void correctCondCheck(User user) {
        List<BadgeEnum> newBadgeList = new ArrayList<>();

        // 뱃지 획득 조건 검사
        if (user.getSolveCount() == 1) {
            newBadgeList.add(BadgeEnum.SOLVED10);
        } else if (user.getSolveCount() == 20) {
            newBadgeList.add(BadgeEnum.SOLVED20);
        } else if (user.getSolveCount() == 30) {
            newBadgeList.add(BadgeEnum.SOLVED30);
        }

        // 새로운 UserBadge 엔티티 생성
        List<UserBadge> newUserBadgeList = newBadgeList.stream()
                .map((badgeEnum) -> {
                    Badge foundBadge = badgeRepository.getReferenceById(badgeEnum);
                    return new UserBadge(null, user, foundBadge, null);
                })
                .toList();

        // 유저 뱃지 저장
        user.getUserBadgeList().addAll(newUserBadgeList);
    }

}
