package com.megamaker.admin.domain.problem.dto;

import com.megamaker.admin.domain.problem.Level;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ResponseProblem {
    private Long id;

    private String title;

    private Level level;

    private Byte score;

    private String params;

    private String returnType;

    private String description;

    private String limitation;

    private String inputOutput;

    private Long solvedCount;

    private Long tryCount;

    private String correctRate;
}
