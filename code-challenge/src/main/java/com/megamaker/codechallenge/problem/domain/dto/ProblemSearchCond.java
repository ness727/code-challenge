package com.megamaker.codechallenge.problem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProblemSearchCond {
    private String title;
    private Integer level;
}
