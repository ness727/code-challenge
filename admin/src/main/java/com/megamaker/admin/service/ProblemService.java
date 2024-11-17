package com.megamaker.admin.service;

import com.megamaker.admin.dto.RequestProblem;
import com.megamaker.admin.dto.problem.ProblemSearchCond;
import com.megamaker.admin.dto.problem.ResponseProblem;
import com.megamaker.admin.entity.Problem;
import com.megamaker.admin.entity.Testcase;
import com.megamaker.admin.mapper.ProblemMapper;
import com.megamaker.admin.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ProblemService {
    private final ProblemMapper problemMapper;
    private final ProblemRepository problemRepository;

    public void save(RequestProblem requestProblem) {
        // 해당 문제 검색
        Problem problem = problemMapper.toProblem(requestProblem);

        // 테스트케이스와 문제 간 엔티티 관계 설정
        List<Testcase> testcaseList = requestProblem.getTestcaseList().stream()
                .map(t -> {
                    Testcase testcase = problemMapper.toTestcase(t);
                    testcase.setProblem(problem);
                    return testcase;
                })
                .toList();
        problem.setTestcaseList(testcaseList);
        problemRepository.save(problem);
    }

    public Page<ResponseProblem> findAll(ProblemSearchCond problemSearchCond, Pageable pageable) {
        return problemRepository.findAll(problemSearchCond, pageable)
                .map(problemMapper::toResponseProblem);
    }
}