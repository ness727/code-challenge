package com.megamaker.codechallenge.userproblem.infra;

import com.megamaker.codechallenge.userproblem.domain.UserProblem;
import com.megamaker.codechallenge.common.infra.BaseDateTime;
import com.megamaker.codechallenge.problem.domain.Problem;
import com.megamaker.codechallenge.problem.infra.ProblemEntity;
import com.megamaker.codechallenge.user.domain.User;
import com.megamaker.codechallenge.user.infra.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user_problems")
@Entity
public class UserProblemEntity extends BaseDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
    @Column(name = "user_id")
    private Long userId;

//    @ManyToOne
//    @JoinColumn(name = "problem_id")
    @Column(name = "problem_id")
    private Long problemId;

    private String answer;

    public UserProblem toModel() {
        return new UserProblem(id, userId, problemId, answer);
    }

    public static UserProblemEntity from(UserProblem userProblem) {
        return new UserProblemEntity(userProblem.getId(), userProblem.getUserId(),
                userProblem.getProblemId(), userProblem.getAnswer());
    }

}
