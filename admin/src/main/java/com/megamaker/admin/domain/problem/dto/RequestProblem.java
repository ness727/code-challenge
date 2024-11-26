package com.megamaker.admin.domain.problem.dto;

import com.megamaker.admin.domain.problem.vo.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestProblem {
    private String title;

    private Level level;

    private Byte score;

    private String params;

    private String returnType;

    private String description;

    private String limitation;

    private String inputOutput;

    private List<RequestTestcase> testcaseList;
}
