package com.megamaker.codechallenge.problem.infra;

import com.megamaker.codechallenge.problem.domain.Problem;
import com.megamaker.codechallenge.problem.domain.ProblemPicture;
import com.megamaker.codechallenge.problem.domain.vo.Level;
import com.megamaker.codechallenge.problem.testcase.domain.Testcase;
import com.megamaker.codechallenge.problem.testcase.infra.TestcaseEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ProblemEntityTest {

    @DisplayName("엔티티 객체를 도메인 객체로 변환 성공")
    @Test
    void entityToModel() {
        // given
        ProblemPictureVO problemPictureVO1 = new ProblemPictureVO("pic0", "http://asdf.com/fsfa.jpg");
        ProblemPictureVO problemPictureVO2 = new ProblemPictureVO("pic1", "http://asdf.com/dfhgdsh.jpg");

        TestcaseEntity testcaseEntity1 = new TestcaseEntity(1, "apple,banana/cherry,apple,banana", "apple");
        TestcaseEntity testcaseEntity2 = new TestcaseEntity(1, "apple,banana/cherry,apple,banana", "apple");

        ProblemEntity problemEntity = ProblemEntity.builder()
                .id(5325L)
                .title("테스트 문제")
                .level(Level.LV2)
                .score((byte) 5)
                .params("int,n,int[],scores")
                .returnType("String")
                .description("test 문제 내용")
                .limitation("배열의 길이는 1 이상 100 이하입니다. 배열의 각 원소는 -1000 이상 1000 이하의 정수입니다.")
                .inputOutput("입력 : [1, 2, 3, 4, 5, 6] 출력 : 12")
                .solvedCount(4L)
                .tryCount(245L)
                .correctRate("80")
                .problemPictureVOList(List.of(problemPictureVO1, problemPictureVO2))
                .testcaseEntityList(List.of(testcaseEntity1, testcaseEntity2))
                .build();

        // when
        Problem problem = problemEntity.toModel();

        // then
        assertThat(problem.getId()).isEqualTo(problemEntity.getId());
        assertThat(problem.getTitle()).isEqualTo(problemEntity.getTitle());
        assertThat(problem.getLevel()).isEqualTo(problemEntity.getLevel());
        assertThat(problem.getScore()).isEqualTo(problemEntity.getScore());
        assertThat(problem.getParams()).isEqualTo(problemEntity.getParams());
        assertThat(problem.getReturnType()).isEqualTo(problemEntity.getReturnType());
        assertThat(problem.getDescription()).isEqualTo(problemEntity.getDescription());
        assertThat(problem.getLimitation()).isEqualTo(problemEntity.getLimitation());
        assertThat(problem.getInputOutput()).isEqualTo(problemEntity.getInputOutput());
        assertThat(problem.getSolvedCount()).isEqualTo(problemEntity.getSolvedCount());
        assertThat(problem.getTryCount()).isEqualTo(problemEntity.getTryCount());
        assertThat(problem.getCorrectRate()).isEqualTo(problemEntity.getCorrectRate());

        List<ProblemPicture> problemPictureList = problem.getProblemPictureList();
        ProblemPicture problemPicture1 = problemPictureList.get(0);
        ProblemPicture problemPicture2 = problemPictureList.get(1);
        assertThat(problemPicture1.getName()).isEqualTo(problemPictureVO1.getName());
        assertThat(problemPicture1.getUrl()).isEqualTo(problemPictureVO1.getUrl());
        assertThat(problemPicture2.getName()).isEqualTo(problemPictureVO2.getName());
        assertThat(problemPicture2.getUrl()).isEqualTo(problemPictureVO2.getUrl());

        List<Testcase> testcaseList = problem.getTestcaseList();
        Testcase testcase1 = testcaseList.get(0);
        Testcase testcase2 = testcaseList.get(0);
        assertThat(testcase1.getId()).isEqualTo(testcaseEntity1.getId());
        assertThat(testcase1.getParamData()).isEqualTo(testcaseEntity1.getParamData());
        assertThat(testcase1.getResult()).isEqualTo(testcaseEntity1.getResult());
        assertThat(testcase2.getId()).isEqualTo(testcaseEntity2.getId());
        assertThat(testcase2.getParamData()).isEqualTo(testcaseEntity2.getParamData());
        assertThat(testcase2.getResult()).isEqualTo(testcaseEntity2.getResult());
    }

    @DisplayName("도메인 객체를 엔티티 객체로 변환 성공")
    @Test
    void modelToEntity() {
        // given
        ProblemPicture problemPicture1 = new ProblemPicture("pic0", "http://asdf.com/fsfa.jpg");
        ProblemPicture problemPicture2 = new ProblemPicture("pic1", "http://asdf.com/dfhgdsh.jpg");

        Testcase testcase1 = new Testcase(1, "apple,banana/cherry,apple,banana", "apple");
        Testcase testcase2 = new Testcase(1, "apple,banana/cherry,apple,banana", "apple");

        Problem problem = Problem.builder()
                .id(5325L)
                .title("테스트 문제")
                .level(Level.LV2)
                .score((byte) 5)
                .params("int,n,int[],scores")
                .returnType("String")
                .description("test 문제 내용")
                .limitation("배열의 길이는 1 이상 100 이하입니다. 배열의 각 원소는 -1000 이상 1000 이하의 정수입니다.")
                .inputOutput("입력 : [1, 2, 3, 4, 5, 6] 출력 : 12")
                .solvedCount(4L)
                .tryCount(245L)
                .correctRate("80")
                .problemPictureList(List.of(problemPicture1, problemPicture2))
                .testcaseList(List.of(testcase1, testcase2))
                .build();

        // when
        ProblemEntity problemEntity = ProblemEntity.from(problem);

        // then
        assertThat(problem.getId()).isEqualTo(problemEntity.getId());
        assertThat(problem.getTitle()).isEqualTo(problemEntity.getTitle());
        assertThat(problem.getLevel()).isEqualTo(problemEntity.getLevel());
        assertThat(problem.getScore()).isEqualTo(problemEntity.getScore());
        assertThat(problem.getParams()).isEqualTo(problemEntity.getParams());
        assertThat(problem.getReturnType()).isEqualTo(problemEntity.getReturnType());
        assertThat(problem.getDescription()).isEqualTo(problemEntity.getDescription());
        assertThat(problem.getLimitation()).isEqualTo(problemEntity.getLimitation());
        assertThat(problem.getInputOutput()).isEqualTo(problemEntity.getInputOutput());
        assertThat(problem.getSolvedCount()).isEqualTo(problemEntity.getSolvedCount());
        assertThat(problem.getTryCount()).isEqualTo(problemEntity.getTryCount());
        assertThat(problem.getCorrectRate()).isEqualTo(problemEntity.getCorrectRate());

        List<ProblemPictureVO> problemPictureVOList = problemEntity.getProblemPictureVOList();
        ProblemPictureVO problemPictureVO1 = problemPictureVOList.get(0);
        ProblemPictureVO problemPictureVO2 = problemPictureVOList.get(1);
        assertThat(problemPicture1.getName()).isEqualTo(problemPictureVO1.getName());
        assertThat(problemPicture1.getUrl()).isEqualTo(problemPictureVO1.getUrl());
        assertThat(problemPicture2.getName()).isEqualTo(problemPictureVO2.getName());
        assertThat(problemPicture2.getUrl()).isEqualTo(problemPictureVO2.getUrl());

        List<TestcaseEntity> testcaseList = problemEntity.getTestcaseEntityList();
        TestcaseEntity testcaseEntity1 = testcaseList.get(0);
        TestcaseEntity testcaseEntity2 = testcaseList.get(0);
        assertThat(testcase1.getId()).isEqualTo(testcaseEntity1.getId());
        assertThat(testcase1.getParamData()).isEqualTo(testcaseEntity1.getParamData());
        assertThat(testcase1.getResult()).isEqualTo(testcaseEntity1.getResult());
        assertThat(testcase2.getId()).isEqualTo(testcaseEntity2.getId());
        assertThat(testcase2.getParamData()).isEqualTo(testcaseEntity2.getParamData());
        assertThat(testcase2.getResult()).isEqualTo(testcaseEntity2.getResult());
    }
}