package com.megamaker.codechallenge.problem.domain.dto;

import com.megamaker.codechallenge.problem.domain.ProblemPicture;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ResponseProblemPicture {
    private String name;
    private String url;

    public static ResponseProblemPicture from(ProblemPicture problemPicture) {
        return new ResponseProblemPicture(problemPicture.getName(), problemPicture.getUrl());
    }
}
