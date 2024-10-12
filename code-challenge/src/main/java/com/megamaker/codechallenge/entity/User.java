package com.megamaker.codechallenge.entity;

import com.megamaker.codechallenge.domain.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User extends BaseTimeDate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    private String nickname;

    private Integer score;

    @Enumerated(EnumType.ORDINAL)
    private Role role;
}

