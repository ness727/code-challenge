package com.megamaker.codechallenge.testcase.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestcaseJpaRepository extends JpaRepository<TestcaseEntity, Long> {
    List<TestcaseEntity> findByProblemId(Long problemId);
}
