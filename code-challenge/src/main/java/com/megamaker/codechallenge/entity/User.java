package com.megamaker.codechallenge.entity;

import com.megamaker.codechallenge.securityconfig.Provider;
import com.megamaker.codechallenge.domain.user.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class User extends BaseTimeDate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Provider provider;

    @Column(name = "provider_id")
    private String providerId;

    private String nickname;

    private Integer score;

    @Enumerated(EnumType.ORDINAL)
    private Role role;
}

