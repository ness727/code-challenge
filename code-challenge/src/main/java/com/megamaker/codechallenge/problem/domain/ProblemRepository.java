package com.megamaker.codechallenge.problem.domain;

import com.megamaker.codechallenge.problem.domain.dto.ProblemSearchCond;
import com.megamaker.codechallenge.problem.domain.vo.Testcase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository {
    Page<Problem> findAll(ProblemSearchCond problemSearchCond, Pageable pageable);

    Optional<Problem> findById(Long id);

    List<Testcase> findTestcaseListById(Long id);
}