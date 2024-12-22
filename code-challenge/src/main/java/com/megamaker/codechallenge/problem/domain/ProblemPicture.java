package com.megamaker.codechallenge.problem.domain;

import com.megamaker.codechallenge.problem.infra.ProblemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProblemPicture {
    private String name;

    private String url;
}
