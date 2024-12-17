package com.megamaker.codechallenge.badge.infra;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.badge.domain.vo.BadgeEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "badges")
@Entity
public class BadgeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BadgeEnum id;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    public static Badge toModel(BadgeEntity badgeEntity) {
        return new Badge(badgeEntity.getId(), badgeEntity.getDescription(), badgeEntity.getImageUrl());
    }

    public static BadgeEntity from(Badge badge) {
        return new BadgeEntity(badge.getId(), badge.getDescription(), badge.getImageUrl());
    }

}
