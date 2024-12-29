package com.megamaker.codechallenge.badge.infra;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.badge.domain.BadgeRepository;
import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@Sql(value = "/sql/badge-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DataJpaTest
class BadgeRepositoryImplTest {
    private final BadgeRepository badgeRepository;

    @Autowired
    public BadgeRepositoryImplTest(BadgeJpaRepository badgeJpaRepository) {
        this.badgeRepository = new BadgeRepositoryImpl(badgeJpaRepository);
    }

    @DisplayName("뱃지 id로 조회 성공")
    @Test
    void findById() {
        // given

        // when
        Badge foundBadge = badgeRepository.findById(BadgeEnum.SOLVED10).orElseThrow();

        // then
        Assertions.assertThat(foundBadge.getId()).isEqualTo(BadgeEnum.SOLVED10);
    }
}