package com.megamaker.codechallenge.common;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user_badges")
@Entity
public class UserBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "badge_id")
    private Badge badge;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
