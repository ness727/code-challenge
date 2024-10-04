package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.domain.Level;
import com.megamaker.codechallenge.dto.ProblemSearchCond;
import com.megamaker.codechallenge.dto.ResponseProblem;
import com.megamaker.codechallenge.entity.Problem;
import com.megamaker.codechallenge.mapper.ProblemMapper;
import com.megamaker.codechallenge.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProblemService {
    private final ProblemRepository problemRepository;

    public List<ResponseProblem> getList(ProblemSearchCond problemSearchCond, Pageable pageable) {
        return problemRepository.findAll(problemSearchCond, pageable).stream()
                .map(ProblemMapper.INSTANCE::toResponseProblem)
                .toList();
    }
}
