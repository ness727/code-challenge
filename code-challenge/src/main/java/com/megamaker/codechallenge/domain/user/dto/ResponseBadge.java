package com.megamaker.codechallenge.domain.user.dto;

import com.megamaker.codechallenge.domain.badge.vo.BadgeEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ResponseBadge {
    private BadgeEnum name;

    private String description;

    private String imageUrl;

    @Setter
    private LocalDate achievedDate;
}
