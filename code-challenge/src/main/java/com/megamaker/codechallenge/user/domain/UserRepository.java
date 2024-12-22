package com.megamaker.codechallenge.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByProviderId(String providerId);

    //Optional<User> findByProviderIdWithUserProblem(String providerId);

    List<User> findTopNByOrderByScoreDesc(int n);

    Optional<String> findAnswerByProviderIdAndProblemId(String providerId, Long problemId);

    void save(User user);
}
