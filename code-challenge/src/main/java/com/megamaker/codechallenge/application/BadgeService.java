package com.megamaker.codechallenge.application;

import com.megamaker.codechallenge.domain.badge.vo.BadgeEnum;
import com.megamaker.codechallenge.domain.badge.Badge;
import com.megamaker.codechallenge.domain.user.User;
import com.megamaker.codechallenge.domain.UserBadge;
import com.megamaker.codechallenge.domain.badge.BadgeRepository;
import com.megamaker.codechallenge.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;
    
    public void correctCondCheck(User user) {
        Set<BadgeEnum> newBadgeSet = new HashSet<>();

        // 뱃지 획득 조건 검사

        // 문제 풀이 수 관련 뱃지
        if (user.getSolveCount() == 10) {
            newBadgeSet.add(BadgeEnum.SOLVED10);
        } else if (user.getSolveCount() == 20) {
            newBadgeSet.add(BadgeEnum.SOLVED20);
        } else if (user.getSolveCount() == 30) {
            newBadgeSet.add(BadgeEnum.SOLVED30);
        }

        // 유저 점수 별 랭킹 관련 뱃지
        List<User> top3UserList = userRepository.findTopNByOrderByScoreDesc(3);

        if (Objects.equals(user.getId(), top3UserList.get(0).getId())) {
            newBadgeSet.add(BadgeEnum.TOP1);
        } else if (Objects.equals(user.getId(), top3UserList.get(1).getId())) {
            newBadgeSet.add(BadgeEnum.TOP2);
        } else if (Objects.equals(user.getId(), top3UserList.get(2).getId())) {
            newBadgeSet.add(BadgeEnum.TOP3);
        }

        // 이미 가지고 있는 뱃지 삭제
        for (UserBadge userBadge : user.getUserBadgeList()) {
            newBadgeSet.remove(userBadge.getBadge().getId());
        }

        // 새로운 UserBadge 엔티티 생성
        List<UserBadge> newUserBadgeList = newBadgeSet.stream()
                .map((badgeEnum) -> {
                    Badge foundBadge = badgeRepository.getReferenceById(badgeEnum);
                    return new UserBadge(null, user, foundBadge, null);
                })
                .toList();

        // 유저 뱃지 저장
        user.getUserBadgeList().addAll(newUserBadgeList);
    }

}
