package com.megamaker.codechallenge.badge.infra;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeEntityTest {

    @DisplayName("BadgeEntity를 Badge 도메인 모델로 변경 성공")
    @Test
    void entityToModel() {
        // given
        BadgeEntity badgeEntity
                = new BadgeEntity(BadgeEnum.SOLVED10, "풀이 10문제 달성", "http://asdf.com");

        // when
        Badge badge = badgeEntity.toModel();

        // then
        Assertions.assertThat(badge.getId()).isEqualTo(badgeEntity.getId());
        Assertions.assertThat(badge.getDescription()).isEqualTo(badgeEntity.getDescription());
        Assertions.assertThat(badge.getImageUrl()).isEqualTo(badgeEntity.getImageUrl());
    }

    @DisplayName("Badge 도메인 모델을 BadgeEntity로 변경 성공")
    @Test
    void modelToEntity() {
        // given
        Badge badge = new Badge(BadgeEnum.SOLVED10, "풀이 10문제 달성", "http://asdf.com");

        // when
        BadgeEntity badgeEntity = BadgeEntity.from(badge);

        // then
        Assertions.assertThat(badge.getId()).isEqualTo(badgeEntity.getId());
        Assertions.assertThat(badge.getDescription()).isEqualTo(badgeEntity.getDescription());
        Assertions.assertThat(badge.getImageUrl()).isEqualTo(badgeEntity.getImageUrl());
    }
}