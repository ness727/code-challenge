package com.megamaker.admin.service;

import com.megamaker.admin.dto.RequestProblem;
import com.megamaker.admin.entity.Problem;
import com.megamaker.admin.mapper.ProblemMapper;
import com.megamaker.admin.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProblemService {
    private final ProblemRepository problemRepository;

    public void save(RequestProblem requestProblem) {
        Problem problem = ProblemMapper.INSTANCE.toProblem(requestProblem);
        problemRepository.save(problem);
    }
}