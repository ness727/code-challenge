package com.megamaker.admin.dto.problem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class ProblemParams {
    private String page;
    private String search;
    private String size;
    private String sort;
}
