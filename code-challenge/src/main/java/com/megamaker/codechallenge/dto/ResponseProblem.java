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
    private String params;
    private String returnType;
    private String description;
    private String limitation;
    private String inputOutput;
    private Float correctRate;
    private List<ResponseProblemPicture> problemPictureList;
    private List<ResponseTestcase> testcaseList;

    public String getParams() {
        String[] split = params.split(",");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < split.length; i += 2) {
            sb.append(split[i]).append(" ").append(split[i + 1]).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        return sb.toString();
    }
}
