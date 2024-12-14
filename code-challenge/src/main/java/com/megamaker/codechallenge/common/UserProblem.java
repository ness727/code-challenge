package com.megamaker.codechallenge.common;

import com.megamaker.codechallenge.problem.domain.Problem;
import com.megamaker.codechallenge.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "user_problems")
@Entity
public class UserProblem extends BaseDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    private String answer;

    public void updateUserAnswer(String answer) {
        this.answer = answer;
    }
}
