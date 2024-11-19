package com.megamaker.admin.repository;

import com.megamaker.admin.dto.problem.ProblemSearchCond;
import com.megamaker.admin.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository {
    Page<Problem> findAll(ProblemSearchCond problemSearchCond, Pageable pageable);

    Optional<Problem> findById(Long id);

    void save(Problem problem);

    void remove(Long id);
}
