package com.megamaker.codechallenge.testcase.domain;

import java.util.List;

public interface TestcaseRepository {
    List<Testcase> findByProblemId(Long problemId);
}
