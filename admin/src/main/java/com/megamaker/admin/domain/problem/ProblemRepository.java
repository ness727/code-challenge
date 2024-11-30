package com.megamaker.admin.domain.problem;

import com.megamaker.admin.domain.problem.dto.ProblemSearchCond;
import com.megamaker.admin.domain.problem.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProblemRepository {
    Page<Problem> findAll(ProblemSearchCond problemSearchCond, Pageable pageable);

    Optional<Problem> findById(Long id);

    void save(Problem problem);

    void removeById(Long id);
}
