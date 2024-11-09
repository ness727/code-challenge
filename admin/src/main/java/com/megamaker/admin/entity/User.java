package com.megamaker.admin.entity;

import com.megamaker.admin.domain.Role;
import com.megamaker.admin.securityconfig.Provider;
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

    @Column(name = "provider_nickname")
    private String providerNickname;

    private String nickname;

    private Integer score;

    @Enumerated(EnumType.ORDINAL)
    private Role role;
}

