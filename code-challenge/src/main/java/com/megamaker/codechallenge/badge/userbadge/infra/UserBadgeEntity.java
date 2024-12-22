package com.megamaker.codechallenge.badge.userbadge.infra;

import com.megamaker.codechallenge.badge.infra.BadgeEntity;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadge;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user_badges")
@Entity
public class UserBadgeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "badge_id")
    private BadgeEntity badgeEntity;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UserBadge toModel() {
        return new UserBadge(id, userId, badgeEntity.toModel(), createdAt);
    }

    public static UserBadgeEntity from(UserBadge userBadge) {
        return new UserBadgeEntity(userBadge.getId(), userBadge.getUserId(),
                BadgeEntity.from(userBadge.getBadge()), userBadge.getCreatedAt());
    }
}
