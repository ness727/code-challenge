package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.dto.ProblemSearchCond;
import com.megamaker.codechallenge.dto.ResponseListProblem;
import com.megamaker.codechallenge.dto.ResponseProblem;
import com.megamaker.codechallenge.mapper.ProblemMapper;
import com.megamaker.codechallenge.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ProblemService {
    private final ProblemRepository problemRepository;
    private final ProblemMapper problemMapper;

    public List<ResponseListProblem> getList(ProblemSearchCond problemSearchCond, Pageable pageable) {
        return problemRepository.findAll(problemSearchCond, pageable).stream()
                .map(problemMapper::toResponseListProblem)
                .toList();
    }

    public ResponseProblem get(Long id) {
        return problemMapper.toResponseProblem(problemRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1)));
    }
}
