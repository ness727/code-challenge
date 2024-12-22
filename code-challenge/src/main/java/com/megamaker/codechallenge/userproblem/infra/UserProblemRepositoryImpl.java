package com.megamaker.codechallenge.userproblem.infra;

import com.megamaker.codechallenge.userproblem.domain.UserProblem;
import com.megamaker.codechallenge.userproblem.domain.UserProblemRepository;
import com.megamaker.codechallenge.problem.infra.ProblemEntity;
import com.megamaker.codechallenge.user.infra.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserProblemRepositoryImpl implements UserProblemRepository {
    private final UserProblemJpaRepository userProblemJpaRepository;

    @Override
    public Optional<UserProblem> findByUserIdAndProblemId(Long userId, Long problemId) {
        return userProblemJpaRepository.findByUserIdAndProblemId(userId, problemId)
                .map(UserProblemEntity::toModel);
    }

    @Override
    public List<UserProblem> findByUserId(Long userId) {
        return userProblemJpaRepository.findByUserId(userId).stream()
                .map(UserProblemEntity::toModel)
                .toList();
    }

    @Override
    public void save(UserProblem userProblem) {
        userProblemJpaRepository.save(UserProblemEntity.from(userProblem));
    }
}
