package com.megamaker.codechallenge.domain.entity;

import com.megamaker.codechallenge.securityconfig.Provider;
import com.megamaker.codechallenge.domain.user.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
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

    @Column(name = "solve_count")
    private Integer solveCount;

    private Integer score;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserBadge> userBadgeList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserProblem> userProblemList;

    public void addScoreAndSolveCount(Byte problemScore) {
        this.score += problemScore;
        this.solveCount++;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}

