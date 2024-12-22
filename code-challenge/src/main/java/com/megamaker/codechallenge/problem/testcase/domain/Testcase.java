package com.megamaker.codechallenge.problem.testcase.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Testcase {
    private Integer id;

//    private Long problemId;

    private String paramData;

    private String result;

}
