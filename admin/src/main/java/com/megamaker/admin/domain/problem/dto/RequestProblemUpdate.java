package com.megamaker.admin.domain.problem.dto;

import com.megamaker.admin.domain.problem.Level;
import com.megamaker.admin.domain.problem.Testcase;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RequestProblemUpdate {
    private Long id;

    private String title;

    private Level level;

    private Byte score;

    private String params;

    private String returnType;

    private String description;

    private String limitation;

    private String inputOutput;

    private List<Testcase> testcaseList;
}
