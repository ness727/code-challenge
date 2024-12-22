package com.megamaker.codechallenge.problem.domain.dto;

import com.megamaker.codechallenge.problem.testcase.domain.Testcase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseTestcase {
    private String paramData;
    private String result;

    public static ResponseTestcase from(Testcase testcase) {
        return new ResponseTestcase(testcase.getParamData(), testcase.getResult());
    }
}
