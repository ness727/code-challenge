package com.megamaker.codechallenge.problem.testcase.infra;

import com.megamaker.codechallenge.problem.infra.ProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestcaseJpaRepository extends JpaRepository<TestcaseEntity, Long> {
    List<TestcaseEntity> findByProblemEntity(ProblemEntity problemEntity);
}
