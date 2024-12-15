package com.megamaker.codechallenge.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJpaImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByProviderProviderId(String providerId) {
        return userJpaRepository.findByProviderProviderId(providerId);
    }

    @Override
    public List<User> findTopNByOrderByScoreDesc(int n) {
        return userJpaRepository.findTopNByOrderByScoreDesc(n);
    }

    @Override
    public Optional<String> findAnswerByProviderIdAndProblemId(String providerId, Long problemId) {
        return userJpaRepository.findAnswerByProviderIdAndProblemId(providerId, problemId);
    }

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }
}
