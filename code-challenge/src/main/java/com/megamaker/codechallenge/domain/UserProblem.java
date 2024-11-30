package com.megamaker.codechallenge.domain;

import com.megamaker.codechallenge.domain.problem.Problem;
import com.megamaker.codechallenge.domain.user.User;
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
