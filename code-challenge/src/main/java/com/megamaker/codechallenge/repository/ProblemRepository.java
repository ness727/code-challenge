package com.megamaker.codechallenge.repository;

import com.megamaker.codechallenge.dto.ProblemSearchCond;
import com.megamaker.codechallenge.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository {
    List<Problem> findAll(ProblemSearchCond problemSearchCond, Pageable pageable);
    Optional<Problem> findById(Long id);
}
