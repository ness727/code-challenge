package com.megamaker.codechallenge.domain.entity;

import com.megamaker.codechallenge.domain.user.BadgeEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "badges")
@Entity
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BadgeEnum id;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;
}
