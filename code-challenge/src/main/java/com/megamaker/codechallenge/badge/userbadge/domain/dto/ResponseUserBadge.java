package com.megamaker.codechallenge.badge.userbadge.domain.dto;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadge;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ResponseUserBadge {
    private BadgeEnum name;

    private String description;

    private String imageUrl;

    private LocalDate achievedDate;

    public static ResponseUserBadge from(UserBadge userBadge) {
        Badge badge = userBadge.getBadge();
        return new ResponseUserBadge(badge.getId(), badge.getDescription(),
                badge.getImageUrl(), userBadge.getCreatedAt().toLocalDate());
    }
}
