package com.megamaker.codechallenge.problem.domain.dto;

import com.megamaker.codechallenge.problem.domain.Problem;
import com.megamaker.codechallenge.problem.domain.vo.Level;
import lombok.*;

import java.util.List;

@Getter
@Builder
public class ResponseProblem {
    private String title;
    private Level level;
    private Byte score;
    private String params;
    private String returnType;
    private String description;
    private String limitation;
    private String inputOutput;
    private String correctRate;
    private List<ResponseProblemPicture> problemPictureList;
    private List<ResponseTestcase> testcaseList;
    private boolean solved;

    public String getParams() {
        String[] split = params.split(",");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < split.length; i += 2) {
            sb.append(split[i]).append(" ").append(split[i + 1]).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        return sb.toString();
    }

    public void setSolvedTrue() {
        this.solved = true;
    }

    public static ResponseProblem from(Problem problem, boolean isSolved) {
        return ResponseProblem.builder()
                .title(problem.getTitle())
                .level(problem.getLevel())
                .score(problem.getScore())
                .params(problem.getParams())
                .returnType(problem.getReturnType())
                .description(problem.getDescription())
                .limitation(problem.getLimitation())
                .inputOutput(problem.getInputOutput())
                .correctRate(problem.getCorrectRate())
                .problemPictureList(
                        problem.getProblemPictureList().stream()
                                .map(ResponseProblemPicture::from)
                                .toList()
                )
                .testcaseList(
                        problem.getTestcaseList().stream()
                                .map(ResponseTestcase::from)
                                .toList()
                )
                .solved(isSolved)
                .build();
    }
}
