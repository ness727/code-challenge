package com.megamaker.codechallenge.testcase.infra;

import com.megamaker.codechallenge.testcase.domain.Testcase;
import com.megamaker.codechallenge.testcase.domain.TestcaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TestcaseRepositoryImpl implements TestcaseRepository {
    private final TestcaseJpaRepository testcaseJpaRepository;

    @Override
    public List<Testcase> findByProblemId(Long problemId) {
        return testcaseJpaRepository.findByProblemId(problemId).stream()
                .map(TestcaseEntity::toModel)
                .toList();
    }
}
