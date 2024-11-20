package com.megamaker.admin.domain.problem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProblemSearchCond {
    private String search;
    private Integer level;
}
