package com.megamaker.codechallenge.problem.testcase.infra;

import com.megamaker.codechallenge.problem.domain.Problem;
import com.megamaker.codechallenge.problem.infra.ProblemEntity;
import com.megamaker.codechallenge.problem.testcase.domain.Testcase;
import com.megamaker.codechallenge.problem.testcase.domain.TestcaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TestcaseRepositoryImpl implements TestcaseRepository {
    private final TestcaseJpaRepository testcaseJpaRepository;

    @Override
    public List<Testcase> findByProblemId(Problem problem) {
        return testcaseJpaRepository.findByProblemEntity(ProblemEntity.from(problem)).stream()
                .map(TestcaseEntity::toModel)
                .toList();
    }
}
