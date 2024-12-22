package com.megamaker.codechallenge.user.infra;

import com.megamaker.codechallenge.user.domain.User;
import com.megamaker.codechallenge.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByProviderId(String providerId) {
        return userJpaRepository.findByProviderId(providerId).map(UserEntity::toModel);
    }

//    @Override
//    public Optional<User> findByProviderIdWithUserProblem(String providerId) {
//        return userJpaRepository.findByProviderIdWithUserProblem(providerId).map(UserEntity::toModel);
//    }

    @Override
    public List<User> findTopNByOrderByScoreDesc(int n) {
        return userJpaRepository.findTopNByOrderByScoreDesc(n).stream()
                .map(UserEntity::toModel)
                .toList();
    }

    @Override
    public Optional<String> findAnswerByProviderIdAndProblemId(String providerId, Long problemId) {
        return userJpaRepository.findAnswerByProviderIdAndProblemId(providerId, problemId);
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(UserEntity.from(user));
    }
}
