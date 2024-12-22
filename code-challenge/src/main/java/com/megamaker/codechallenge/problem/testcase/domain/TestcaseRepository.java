package com.megamaker.codechallenge.problem.testcase.domain;

import com.megamaker.codechallenge.problem.domain.Problem;

import java.util.List;

public interface TestcaseRepository {
    List<Testcase> findByProblemId(Problem problem);
}
