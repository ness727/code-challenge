package com.megamaker.codechallenge.problem.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ResponseListProblem {
    private Long id;
    private String title;
    private String level;
    private Float correctRate;
}
