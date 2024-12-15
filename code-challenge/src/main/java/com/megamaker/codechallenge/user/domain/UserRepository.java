package com.megamaker.codechallenge.user.domain;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByProviderProviderId(String providerId);
    List<User> findTopNByOrderByScoreDesc(int n);
    Optional<String> findAnswerByProviderIdAndProblemId(String providerId, Long problemId);
    void save(User user);
}
