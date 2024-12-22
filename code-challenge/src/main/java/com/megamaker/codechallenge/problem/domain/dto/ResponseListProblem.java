package com.megamaker.codechallenge.problem.domain.dto;

import com.megamaker.codechallenge.problem.domain.Problem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ResponseListProblem {
    private final Long id;
    private final String title;
    private final String level;
    private final String correctRate;

    public static ResponseListProblem from(Problem problem) {
        return new ResponseListProblem(problem.getId(), problem.getTitle(),
                problem.getLevel().name(), problem.getCorrectRate());
    }
}
