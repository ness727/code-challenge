package com.megamaker.codechallenge.dto.user;

import com.megamaker.codechallenge.domain.user.BadgeEnum;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
