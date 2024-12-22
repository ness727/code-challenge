package com.megamaker.codechallenge.problem.domain;

import com.megamaker.codechallenge.problem.domain.vo.Level;
import com.megamaker.codechallenge.problem.testcase.domain.Testcase;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Problem {
    private final Long id;

    private final String title;

    private final Level level;

    private final Byte score;

    private final String params;

    private final String returnType;

    private final String description;

    private final String limitation;

    private final String inputOutput;

    private final Long solvedCount;

    private final Long tryCount;

    private final String correctRate;

    private final List<ProblemPicture> problemPictureList;

     private final List<Testcase> testcaseList;

//    public void addTestcase(Testcase testcase) {
//        testcaseList.add(testcase);
//    }

//    public Problem update(Problem problem) {
//        return Problem.builder()
//                .id(problem.getId())
//                .title(problem.getTitle())
//                .level(problem.getLevel())
//                .score(problem.getScore())
//                .params(problem.getParams())
//                .returnType(problem.getReturnType())
//                .description(problem.getDescription())
//                .limitation(problem.getLimitation())
//                .inputOutput(problem.getInputOutput())
//                .solvedCount(problem.getSolvedCount())
//                .tryCount(problem.getTryCount())
//                .correctRate(problem.getCorrectRate())
//                .problemPictureList(problem.getProblemPictureList())
//                .testcaseList(problem.getTestcaseList())
//                .build();
//    }

    public static Problem increaseCorrectAnswerCount(Problem problem) {
        Long newSolvedCount = problem.getSolvedCount() + 1;
        Long newTryCount = problem.getSolvedCount() + 1;

        return Problem.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .level(problem.getLevel())
                .score(problem.getScore())
                .params(problem.getParams())
                .returnType(problem.getReturnType())
                .description(problem.getDescription())
                .limitation(problem.getLimitation())
                .inputOutput(problem.getInputOutput())
                .solvedCount(newSolvedCount)
                .tryCount(newTryCount)
                .correctRate(
                        reCalcCorrectRate(newSolvedCount, newTryCount)
                )
                .problemPictureList(problem.getProblemPictureList())
//                .testcaseList(problem.getTestcaseList())
                .build();

    }

    public static Problem increaseWrongAnswerCount(Problem problem) {
        Long newTryCount = problem.getSolvedCount() + 1;

        return Problem.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .level(problem.getLevel())
                .score(problem.getScore())
                .params(problem.getParams())
                .returnType(problem.getReturnType())
                .description(problem.getDescription())
                .limitation(problem.getLimitation())
                .inputOutput(problem.getInputOutput())
                .solvedCount(problem.getSolvedCount())
                .tryCount(newTryCount)
                .correctRate(
                        reCalcCorrectRate(problem.getSolvedCount(), newTryCount)
                )
                .problemPictureList(problem.getProblemPictureList())
//                .testcaseList(problem.getTestcaseList())
                .build();
    }

    private static String reCalcCorrectRate(Long solvedCount, Long tryCount) {
        return String.valueOf((Float.valueOf(solvedCount) / tryCount) * 100).split("\\.")[0];
    }
}
