package com.megamaker.codechallenge.badge.application;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadge;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadgeRepository;
import com.megamaker.codechallenge.mock.FakeUserBadgeRepository;
import com.megamaker.codechallenge.mock.FakeUserRepository;
import com.megamaker.codechallenge.user.domain.User;
import com.megamaker.codechallenge.user.domain.UserRepository;
import com.megamaker.codechallenge.user.domain.vo.Provider;
import com.megamaker.codechallenge.user.domain.vo.Role;
import com.megamaker.codechallenge.web.securityconfig.ProviderEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeServiceTest {
    private UserRepository userRepository;
    private UserBadgeRepository userBadgeRepository;
    private BadgeService badgeService;

    @BeforeEach
    void init() {
        userRepository = new FakeUserRepository();
        userBadgeRepository = new FakeUserBadgeRepository();
        badgeService = new BadgeService(userRepository, userBadgeRepository);
    }

    @DisplayName("회원이 1명일 때, 새로운 [풀이 수 10, 랭킹 1위] 뱃지 획득에 성공한다.")
    @Test
    void getNewBadgesSuccess() {
        // given
        Provider provider = new Provider(ProviderEnum.GITHUB, "1654623", "nnaaww");
        User user = User.builder()
                .provider(provider)
                .nickname("my-nickname")
                .solveCount(10)
                .score(15)
                .role(Role.USER)
                .build();

        // when
        userRepository.save(user);

        user = userRepository.findByProviderId(provider.getProviderId()).orElseThrow();
        Set<BadgeEnum> newBadgeSet = badgeService.getNewBadgeSet(user);

        // then
        assertThat(newBadgeSet.contains(BadgeEnum.SOLVED10)).isTrue();
        assertThat(newBadgeSet.contains(BadgeEnum.TOP1)).isTrue();
        assertThat(newBadgeSet.size()).isEqualTo(2);
    }

    @DisplayName("회원이 3명일 때, 2등이 새로운 [풀이 수 20, 랭킹 2위] 뱃지 획득에 성공한다.")
    @Test
    void getNewBadgesSuccess2() {
        // given
        Provider provider1 = new Provider(ProviderEnum.GITHUB, "5342626", "nnaaww");
        Provider provider2 = new Provider(ProviderEnum.GITHUB, "6456324", "nnaaww");
        Provider provider3 = new Provider(ProviderEnum.GITHUB, "2626457", "nnaaww");
        User user1 = User.builder()
                .provider(provider1)
                .solveCount(10)
                .score(3)
                .build();
        User user2 = User.builder()
                .provider(provider2)
                .solveCount(20)
                .score(2)
                .build();
        User user3 = User.builder()
                .provider(provider3)
                .solveCount(30)
                .score(1)
                .build();

        // when
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        user2 = userRepository.findByProviderId(provider2.getProviderId()).orElseThrow();
        Set<BadgeEnum> newBadgeSet = badgeService.getNewBadgeSet(user2);

        // then
        assertThat(newBadgeSet.contains(BadgeEnum.SOLVED20)).isTrue();
        assertThat(newBadgeSet.contains(BadgeEnum.TOP2)).isTrue();
        assertThat(newBadgeSet.size()).isEqualTo(2);
    }

    @DisplayName("중복 획득한 뱃지는 새로 추가하지 않는다.")
    @Test
    void removeDuplicatedBadgeSuccess() {
        // given
        Provider provider1 = new Provider(ProviderEnum.GITHUB, "5342626", "nnaaww");
        Provider provider2 = new Provider(ProviderEnum.GITHUB, "6456324", "nnaaww");
        Provider provider3 = new Provider(ProviderEnum.GITHUB, "2626457", "nnaaww");
        User user1 = User.builder()
                .provider(provider1)
                .solveCount(10)
                .score(3)
                .build();
        User user2 = User.builder()
                .provider(provider2)
                .solveCount(20)
                .score(2)
                .build();
        User user3 = User.builder()
                .provider(provider3)
                .solveCount(30)
                .score(1)
                .build();

        // when
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        // 뱃지 처음 획득할 때 저장 처리
        user2 = userRepository.findByProviderId(provider2.getProviderId()).orElseThrow();
        Set<BadgeEnum> newBadgeSet = badgeService.getNewBadgeSet(user2);
        Long user2Id = user2.getId();
        userBadgeRepository.saveAll(
                newBadgeSet.stream()
                        .map(badgeEnum -> new UserBadge(null, user2Id,
                                new Badge(badgeEnum, null, null), null)
                        )
                        .toList()
        );

        // 문제 풀고 뱃지 획득 조건 한번 더 조회
        newBadgeSet = badgeService.getNewBadgeSet(user2);

        // then
        assertThat(newBadgeSet.contains(BadgeEnum.SOLVED20)).isFalse();
        assertThat(newBadgeSet.contains(BadgeEnum.TOP2)).isFalse();
        assertThat(newBadgeSet.size()).isEqualTo(0);
    }
}