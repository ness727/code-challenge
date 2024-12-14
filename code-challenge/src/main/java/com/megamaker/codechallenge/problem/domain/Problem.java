package com.megamaker.codechallenge.problem.domain;

import com.megamaker.codechallenge.common.BaseDateTime;
import com.megamaker.codechallenge.problem.domain.vo.Level;
import com.megamaker.codechallenge.problem.domain.vo.ProblemPicture;
import com.megamaker.codechallenge.problem.domain.vo.Testcase;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@ToString
@DynamicInsert
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "problems")
@Entity
public class Problem extends BaseDateTime {
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

    @ElementCollection
    @CollectionTable(name = "problem_pictures", joinColumns = @JoinColumn(name = "problem_id"))
    @OrderColumn(name = "problem_picture_idx")
    private List<ProblemPicture> problemPictureList;

    @ElementCollection
    @CollectionTable(name = "testcases", joinColumns = @JoinColumn(name = "problem_id"))
    @OrderColumn(name = "testcase_idx")
    private List<Testcase> testcaseList;

    public void addTestcase(Testcase testcase) {
        testcaseList.add(testcase);
    }

    public void increaseCorrectAnswerCount() {
        this.solvedCount++;
        this.tryCount++;
        reCalcCorrectRate();
    }

    public void increaseWrongAnswerCount() {
        this.tryCount++;
        reCalcCorrectRate();
    }

    private void reCalcCorrectRate() {
        this.correctRate = String.valueOf((Float.valueOf(solvedCount) / tryCount) * 100).split("\\.")[0];
    }
}
