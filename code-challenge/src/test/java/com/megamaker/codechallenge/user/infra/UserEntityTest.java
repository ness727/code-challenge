package com.megamaker.codechallenge.user.infra;

import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import com.megamaker.codechallenge.badge.infra.BadgeEntity;
import com.megamaker.codechallenge.badge.userbadge.infra.UserBadgeEntity;
import com.megamaker.codechallenge.user.domain.vo.Provider;
import com.megamaker.codechallenge.userproblem.infra.UserProblemEntity;
import com.megamaker.codechallenge.problem.infra.ProblemEntity;
import com.megamaker.codechallenge.user.domain.User;
import com.megamaker.codechallenge.user.domain.vo.Role;
import com.megamaker.codechallenge.web.securityconfig.ProviderEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class UserEntityTest {

    @Test
    void UserEntity를_User_도메인_모델로_변환_성공() {
        // given
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .provider(ProviderEnum.GITHUB)
                .providerId("12341234")
                .providerNickname("github-nickname")
                .nickname("my-nickname")
                .solveCount(2)
                .role(Role.USER)
                .build();

        // when
        User user = userEntity.toModel();

        // then
        Assertions.assertThat(user.getId()).isEqualTo(userEntity.getId());
        Assertions.assertThat(user.getProvider().getProvider()).isEqualTo(userEntity.getProvider());
        Assertions.assertThat(user.getProvider().getProviderId()).isEqualTo(userEntity.getProviderId());
        Assertions.assertThat(user.getProvider().getProviderNickname()).isEqualTo(userEntity.getProviderNickname());
        Assertions.assertThat(user.getNickname()).isEqualTo(userEntity.getNickname());
        Assertions.assertThat(user.getSolveCount()).isEqualTo(userEntity.getSolveCount());
        Assertions.assertThat(user.getRole()).isEqualTo(userEntity.getRole());
    }

    @Test
    void User_도메인_모델을_UserEntity로_변환_성공() {
        // given
        Provider provider = new Provider(ProviderEnum.GITHUB, "1654623", "nnaaww");

        User user = User.builder()
                .id(1L)
                .provider(provider)
                .nickname("my-nickname")
                .solveCount(2)
                .role(Role.USER)
                .build();

        // when
        UserEntity userEntity = UserEntity.from(user);

        // then
        Assertions.assertThat(user.getId()).isEqualTo(userEntity.getId());
        Assertions.assertThat(user.getProvider().getProvider()).isEqualTo(userEntity.getProvider());
        Assertions.assertThat(user.getProvider().getProviderId()).isEqualTo(userEntity.getProviderId());
        Assertions.assertThat(user.getProvider().getProviderNickname()).isEqualTo(userEntity.getProviderNickname());
        Assertions.assertThat(user.getNickname()).isEqualTo(userEntity.getNickname());
        Assertions.assertThat(user.getSolveCount()).isEqualTo(userEntity.getSolveCount());
        Assertions.assertThat(user.getRole()).isEqualTo(userEntity.getRole());
    }
}