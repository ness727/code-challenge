package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.dto.ProblemSearchCond;
import com.megamaker.codechallenge.dto.ResponseListProblem;
import com.megamaker.codechallenge.dto.ResponseProblem;
import com.megamaker.codechallenge.mapper.ProblemMapper;
import com.megamaker.codechallenge.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProblemService {
    private final ProblemRepository problemRepository;

    public List<ResponseListProblem> getList(ProblemSearchCond problemSearchCond, Pageable pageable) {
        return problemRepository.findAll(problemSearchCond, pageable).stream()
                .map(ProblemMapper.INSTANCE::toResponseListProblem)
                .toList();
    }

    public ResponseProblem get(Long id) {
        return ProblemMapper.INSTANCE.toResponseProblem(problemRepository.find(id));
    }
}
