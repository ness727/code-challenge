package com.megamaker.codechallenge.badge.userbadge.application;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadge;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadgeRepository;
import com.megamaker.codechallenge.mock.FakeUserBadgeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserBadgeServiceTest {
    private final UserBadgeRepository userBadgeRepository;
    private final UserBadgeService userBadgeService;

    public UserBadgeServiceTest() {
        userBadgeRepository = new FakeUserBadgeRepository();
        userBadgeService = new UserBadgeService(userBadgeRepository);
    }

    @DisplayName("UserBadgeList 조회에 성공한다.")
    @Test
    void getUserBadgeListSuccess() {
        // given
        Long userId = 1L;
        LocalDateTime localDateTime = LocalDateTime.now();
        Badge badge1 = new Badge(BadgeEnum.SOLVED10, "문제 풀이 10 달성", "asdf.jpg");
        Badge badge2 = new Badge(BadgeEnum.TOP1, "Top 1 달성", "fdjf.jpg");
        UserBadge userBadge1 = new UserBadge(null, userId, badge1, localDateTime);
        UserBadge userBadge2 = new UserBadge(null, userId, badge2, localDateTime);

        // when
        userBadgeRepository.saveAll(List.of(userBadge1, userBadge2));
        List<UserBadge> userBadgeList = userBadgeService.getUserBadgeList(userId);

        // then
        Assertions.assertThat(userBadgeList.size()).isGreaterThan(0);

        Assertions.assertThat(userBadgeList.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(userBadgeList.get(0).getUserId()).isEqualTo(userId);
        Assertions.assertThat(userBadgeList.get(0).getBadge().getId()).isEqualTo(badge1.getId());
        Assertions.assertThat(userBadgeList.get(0).getCreatedAt()).isEqualTo(localDateTime);

        Assertions.assertThat(userBadgeList.get(1).getId()).isEqualTo(2);
        Assertions.assertThat(userBadgeList.get(1).getUserId()).isEqualTo(userId);
        Assertions.assertThat(userBadgeList.get(1).getBadge().getId()).isEqualTo(badge2.getId());
        Assertions.assertThat(userBadgeList.get(1).getCreatedAt()).isEqualTo(localDateTime);
    }
}