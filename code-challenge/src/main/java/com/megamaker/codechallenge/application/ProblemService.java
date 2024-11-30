package com.megamaker.codechallenge.application;

import com.megamaker.codechallenge.application.exception.UserNotFoundException;
import com.megamaker.codechallenge.domain.problem.dto.ProblemSearchCond;
import com.megamaker.codechallenge.domain.problem.dto.ResponseListProblem;
import com.megamaker.codechallenge.domain.problem.dto.ResponseProblem;
import com.megamaker.codechallenge.domain.problem.mapper.ProblemMapper;
import com.megamaker.codechallenge.domain.problem.ProblemRepository;
import com.megamaker.codechallenge.domain.user.User;
import com.megamaker.codechallenge.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ProblemService {
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final ProblemMapper problemMapper;

    public List<ResponseListProblem> getList(ProblemSearchCond problemSearchCond, Pageable pageable) {
        return problemRepository.findAll(problemSearchCond, pageable).stream()
                .map(problemMapper::toResponseListProblem)
                .toList();
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
