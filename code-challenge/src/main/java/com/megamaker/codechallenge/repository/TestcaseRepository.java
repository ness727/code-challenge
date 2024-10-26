package com.megamaker.codechallenge.repository;

import com.megamaker.codechallenge.entity.Testcase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestcaseRepository extends JpaRepository<Testcase, Long> {
    List<Testcase> findByProblemId(Long problemId);
}
