package com.megamaker.codechallenge.dto;

import com.megamaker.codechallenge.domain.problem.Level;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ResponseProblem {
    private String title;
    private Level level;
    private Byte score;
    private String description;
    private String limitation;
    private String inputOutput;
    private Float correctRate;
    private List<ResponseProblemPicture> problemPictureList;
    private List<ResponseTestcase> testcaseList;
}
