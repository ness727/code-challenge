package com.megamaker.codechallenge.user.infra;

import com.megamaker.codechallenge.common.infra.BaseDateTime;
import com.megamaker.codechallenge.user.domain.User;
import com.megamaker.codechallenge.user.domain.vo.Provider;
import com.megamaker.codechallenge.user.domain.vo.Role;
import com.megamaker.codechallenge.web.securityconfig.ProviderEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class UserEntity extends BaseDateTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private ProviderEnum provider;

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

//    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.PERSIST)
//    private List<UserBadgeEntity> userBadgeEntityList;
//
//    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.PERSIST)
//    private List<UserProblemEntity> userProblemEntityList;

    public User toModel() {
        Provider userProvider = new Provider(provider, providerId, providerNickname);
        User user = User.builder()
                .id(id)
                .nickname(nickname)
                .provider(userProvider)
                .role(role)
                .score(score)
                .solveCount(solveCount)
//                .userBadgeList(new ArrayList<>())
//                .userProblemList(new ArrayList<>())
                .build();

//        List<UserBadge> userBadgeList = userBadgeEntityList.stream()
//                .map(userBadgeEntity ->
//                        userBadgeEntity.toModel(user, userBadgeEntity.getBadgeEntity().toModel())
//                ).toList();
//        user.getUserBadgeList().addAll(userBadgeList);
//
//        List<UserProblem> userProblemList = userProblemEntityList.stream()
//                .map(userProblemEntity ->
//                        userProblemEntity.toModel(user, userProblemEntity.getProblemEntity().toModel())
//                ).toList();
//        user.getUserProblemList().addAll(userProblemList);

        return user;
    }

    public static UserEntity from(User user) {
        Provider userProvider = user.getProvider();

        UserEntity userEntity = UserEntity.builder()
                .id(user.getId())
                .provider(userProvider.getProvider())
                .providerId(userProvider.getProviderId())
                .providerNickname(userProvider.getProviderNickname())
                .nickname(user.getNickname())
                .score(user.getScore())
                .solveCount(user.getSolveCount())
                .role(user.getRole())
//                .userBadgeEntityList(new ArrayList<>())
//                .userProblemEntityList(new ArrayList<>())
                .build();

//        List<UserBadgeEntity> userBadgeList = user.getUserBadgeList().stream()
//                .map(userBadge ->
//                        UserBadgeEntity.from(userBadge, userEntity, BadgeEntity.from(userBadge.getBadge()))
//                ).toList();
//        userEntity.getUserBadgeEntityList().addAll(userBadgeList);
//
//        List<UserProblemEntity> userProblemList = user.getUserProblemList().stream()
//                .map(userProblem ->
//                        UserProblemEntity.from(userProblem, userEntity, ProblemEntity.from(userProblem.getProblem()))
//                ).toList();
//        userEntity.getUserProblemEntityList().addAll(userProblemList);

        return userEntity;
    }
}

