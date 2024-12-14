package com.megamaker.codechallenge.domain.user;

import com.megamaker.codechallenge.domain.BaseDateTime;
import com.megamaker.codechallenge.domain.UserBadge;
import com.megamaker.codechallenge.domain.UserProblem;
import com.megamaker.codechallenge.domain.user.vo.Provider;
import com.megamaker.codechallenge.securityconfig.ProviderEnum;
import com.megamaker.codechallenge.domain.user.vo.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class User extends BaseDateTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Provider provider;

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

