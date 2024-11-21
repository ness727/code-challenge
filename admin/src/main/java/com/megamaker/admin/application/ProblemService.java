package com.megamaker.admin.application;

import com.megamaker.admin.domain.problem.dto.RequestProblem;
import com.megamaker.admin.domain.problem.dto.ProblemSearchCond;
import com.megamaker.admin.domain.problem.dto.RequestProblemUpdate;
import com.megamaker.admin.domain.problem.dto.ResponseListProblem;
import com.megamaker.admin.domain.problem.dto.ResponseProblem;
import com.megamaker.admin.domain.problem.Problem;
import com.megamaker.admin.domain.problem.Testcase;
import com.megamaker.admin.domain.problem.mapper.ProblemMapper;
import com.megamaker.admin.domain.problem.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProblemService {
    private final ProblemMapper problemMapper;
    private final ProblemRepository problemRepository;

    @Transactional
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

    public Page<ResponseListProblem> findAll(ProblemSearchCond problemSearchCond, Pageable pageable) {
        return problemRepository.findAll(problemSearchCond, pageable)
                .map(problemMapper::toResponseListProblem);
    }

    public ResponseProblem findById(Long id) {
        Problem foundProblem = problemRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return problemMapper.toResponseProblem(foundProblem);
    }

    @Transactional
    public ResponseProblem update(RequestProblemUpdate requestProblemUpdate) {
        Problem foundProblem = problemRepository.findById(requestProblemUpdate.getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        foundProblem.update(requestProblemUpdate);
        return problemMapper.toResponseProblem(foundProblem);
    }

    @Transactional
    public void remove(Long id) {
        problemRepository.removeById(id);
    }
}