package com.megamaker.codechallenge.problem.application;

import com.megamaker.codechallenge.problem.domain.dto.ProblemSearchCond;
import com.megamaker.codechallenge.problem.domain.dto.ResponseListProblem;
import com.megamaker.codechallenge.problem.domain.dto.ResponseProblem;
import com.megamaker.codechallenge.problem.mapper.ProblemMapper;
import com.megamaker.codechallenge.problem.domain.ProblemRepository;
import com.megamaker.codechallenge.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ProblemService {
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final ProblemMapper problemMapper;

    public Page<ResponseListProblem> getList(ProblemSearchCond problemSearchCond, Pageable pageable) {
        return problemRepository.findAll(problemSearchCond, pageable)
                .map(problemMapper::toResponseListProblem);
    }

    public ResponseProblem findById(Long id, String providerId) {
        ResponseProblem responseProblem = problemMapper.toResponseProblem(problemRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1)));
        Optional<String> foundUserAnswer = userRepository.findAnswerByProviderIdAndProblemId(providerId, id);

        // 해당 사용자가 이미 푼 문제일 때 이전 유저 답 추가
        if (foundUserAnswer.isPresent()) {
            responseProblem.setSolvedTrue();
        }
        return responseProblem;
    }
}
