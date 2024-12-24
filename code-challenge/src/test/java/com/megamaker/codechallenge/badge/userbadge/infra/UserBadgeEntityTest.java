package com.megamaker.codechallenge.badge.userbadge.infra;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import com.megamaker.codechallenge.badge.infra.BadgeEntity;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadge;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserBadgeEntityTest {

    @DisplayName("엔티티 객체를 도메인 객체로 변환 성공")
    @Test
    void entityToModel() {
        // given
        BadgeEntity badgeEntity = new BadgeEntity(BadgeEnum.SOLVED10, "풀이 10 달성", "http://asdf.com");
        UserBadgeEntity userBadgeEntity = new UserBadgeEntity(1L, 14L, badgeEntity, LocalDateTime.now());

        // when
        UserBadge userBadge = userBadgeEntity.toModel();

        // then
        assertThat(userBadge.getId()).isEqualTo(userBadgeEntity.getId());
        assertThat(userBadge.getUserId()).isEqualTo(userBadgeEntity.getUserId());
        assertThat(userBadge.getBadge().getId()).isEqualTo(userBadgeEntity.getBadgeEntity().getId());
        assertThat(userBadge.getBadge().getDescription()).isEqualTo(userBadgeEntity.getBadgeEntity().getDescription());
        assertThat(userBadge.getBadge().getImageUrl()).isEqualTo(userBadgeEntity.getBadgeEntity().getImageUrl());
        assertThat(userBadge.getCreatedAt()).isEqualTo(userBadgeEntity.getCreatedAt());
    }

    @DisplayName("도메인 객체를 엔티티 객체로 변환 성공")
    @Test
    void modelToEntity() {
        // given
        Badge badge = new Badge(BadgeEnum.SOLVED10, "풀이 10 달성", "http://asdf.com");
        UserBadge userBadge = new UserBadge(1L, 14L, badge, LocalDateTime.now());

        // when
        UserBadgeEntity userBadgeEntity = UserBadgeEntity.from(userBadge);

        // then
        assertThat(userBadge.getId()).isEqualTo(userBadgeEntity.getId());
        assertThat(userBadge.getUserId()).isEqualTo(userBadgeEntity.getUserId());
        assertThat(userBadge.getBadge().getId()).isEqualTo(userBadgeEntity.getBadgeEntity().getId());
        assertThat(userBadge.getBadge().getDescription()).isEqualTo(userBadgeEntity.getBadgeEntity().getDescription());
        assertThat(userBadge.getBadge().getImageUrl()).isEqualTo(userBadgeEntity.getBadgeEntity().getImageUrl());
        assertThat(userBadge.getCreatedAt()).isEqualTo(userBadgeEntity.getCreatedAt());
    }
}