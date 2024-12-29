package com.megamaker.codechallenge.mock;

import com.megamaker.codechallenge.user.domain.User;
import com.megamaker.codechallenge.user.infra.UserEntity;
import com.megamaker.codechallenge.user.domain.UserRepository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeUserRepository implements UserRepository {
    private final List<User> userList = new ArrayList<>();
    private long id;

    @Override
    public Optional<User> findByProviderId(String providerId) {
        return userList.stream()
                .filter(user -> providerId.equals(user.getProvider().getProviderId()))
                .findFirst();
    }

    @Override
    public List<User> findTopNByOrderByScoreDesc(int n) {
        return userList.stream()
                .sorted((user1, user2) -> Integer.compare(user2.getScore(), user1.getScore()))
                .limit(n)
                .toList();
    }

    @Override
    public Optional<String> findAnswerByProviderIdAndProblemId(String providerId, Long problemId) {
        return Optional.of("test_answer");
    }

    @Override
    public void save(User user) {
        User newUser = User.builder()
                .id(increaseIdAndGet())
                .nickname(user.getNickname())
                .provider(user.getProvider())
                .role(user.getRole())
                .score(user.getScore())
                .solveCount(user.getSolveCount())
                .build();
        userList.add(newUser);
    }

    private long increaseIdAndGet() {
        return ++id;
    }
}
