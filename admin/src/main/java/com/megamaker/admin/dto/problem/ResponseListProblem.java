package com.megamaker.admin.dto.problem;

import com.megamaker.admin.domain.Level;
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

    private Level level;

    private Byte score;

    private Long solvedCount;

    private Long tryCount;

    private Float correctRate;
}
