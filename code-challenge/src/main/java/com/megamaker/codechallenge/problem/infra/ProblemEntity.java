package com.megamaker.codechallenge.problem.infra;

import com.megamaker.codechallenge.common.infra.BaseDateTime;
import com.megamaker.codechallenge.problem.domain.Problem;
import com.megamaker.codechallenge.problem.domain.vo.Level;
import com.megamaker.codechallenge.problem.testcase.infra.TestcaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "problems")
@Entity
public class ProblemEntity extends BaseDateTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated
    private Level level;

    private Byte score;

    private String params;

    @Column(name = "return_type")
    private String returnType;

    private String description;

    private String limitation;

    @Column(name = "input_output")
    private String inputOutput;

    @Column(name = "solved_count")
    private Long solvedCount;

    @Column(name = "try_count")
    private Long tryCount;

    @Column(name = "correct_rate")
    private String correctRate;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "problem_pictures", joinColumns = @JoinColumn(name = "problem_id"))
    @OrderColumn(name = "idx")
    private List<ProblemPictureVO> problemPictureVOList;

//    @ElementCollection(fetch = FetchType.LAZY)
//    @CollectionTable(name = "testcases", joinColumns = @JoinColumn(name = "problem_id"))
//    @OrderColumn(name = "testcase_idx")
    @OneToMany(mappedBy = "problemEntity")
    private List<TestcaseEntity> testcaseEntityList;

    public Problem toModel() {
        Problem problem = Problem.builder()
                .id(id)
                .title(title)
                .level(level)
                .score(score)
                .params(params)
                .returnType(returnType)
                .description(description)
                .limitation(limitation)
                .inputOutput(inputOutput)
                .solvedCount(solvedCount)
                .tryCount(tryCount)
                .correctRate(correctRate)
                .problemPictureList(new ArrayList<>())
                .testcaseList(
                        testcaseEntityList.stream()
                                .map(TestcaseEntity::toModel)
                                .toList()
                )
                .build();

        if (problemPictureVOList != null && !problemPictureVOList.isEmpty()) {
            problem.getProblemPictureList().addAll(
                    problemPictureVOList.stream()
                            .map(ProblemPictureVO::toModel)
                            .toList()
            );
        }

        return problem;
    }

    public static ProblemEntity from(Problem problem) {
        return ProblemEntity.builder()
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
                .tryCount(problem.getTryCount())
                .correctRate(problem.getCorrectRate())
                .problemPictureVOList(
                        problem.getProblemPictureList().stream()
                                .map(ProblemPictureVO::from)
                                .toList()
                )
                .testcaseEntityList(
                        problem.getTestcaseList().stream()
                                .map(TestcaseEntity::from)
                                .toList()
                )
                .build();
    }

    public void update(Problem problem) {
        title = problem.getTitle();
        level = problem.getLevel();
        score = problem.getScore();
        params = problem.getParams();
        returnType = problem.getReturnType();
        description = problem.getDescription();
        limitation = problem.getLimitation();
        inputOutput = problem.getInputOutput();
        solvedCount = problem.getSolvedCount();
        tryCount = problem.getTryCount();
        correctRate = problem.getCorrectRate();
        problemPictureVOList = problem.getProblemPictureList().stream()
                .map(ProblemPictureVO::from)
                .toList();
        testcaseEntityList = problem.getTestcaseList().stream()
                .map(TestcaseEntity::from)
                .toList();
    }
}
