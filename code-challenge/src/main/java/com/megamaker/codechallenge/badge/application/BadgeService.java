package com.megamaker.codechallenge.badge.application;

import com.megamaker.codechallenge.badge.userbadge.domain.UserBadge;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadgeRepository;
import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import com.megamaker.codechallenge.user.domain.User;
import com.megamaker.codechallenge.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class BadgeService {
    private final UserRepository userRepository;
    private final UserBadgeRepository userBadgeRepository;

    public Set<BadgeEnum> getNewBadgeSet(User user) {
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

        if (!top3UserList.isEmpty()) {
            if (Objects.equals(user.getId(), top3UserList.get(0).getId())) {
                newBadgeSet.add(BadgeEnum.TOP1);
            } else if (Objects.equals(user.getId(), top3UserList.get(1).getId())) {
                newBadgeSet.add(BadgeEnum.TOP2);
            } else if (Objects.equals(user.getId(), top3UserList.get(2).getId())) {
                newBadgeSet.add(BadgeEnum.TOP3);
            }
        }
        // 이미 가지고 있는 뱃지 삭제
//        for (UserBadge userBadge : user.getUserBadgeList()) {
//            newBadgeSet.remove(userBadge.getBadge().getId());
//        }
        List<UserBadge> userBadgeList = userBadgeRepository.findByUserId(user.getId());
        if (userBadgeList != null && !userBadgeList.isEmpty()) {
            userBadgeList.forEach(userBadge -> newBadgeSet.remove(userBadge.getBadge().getId()));
        }

        // 새로운 유저 뱃지 리턴
        return newBadgeSet;
    }
}
