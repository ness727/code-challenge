package com.megamaker.codechallenge.dto;

import com.megamaker.codechallenge.domain.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProblemSearchCond {
    private String title;
    private Integer level;
}
