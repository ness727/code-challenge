package com.megamaker.codechallenge.user.application;

import com.megamaker.codechallenge.problem.exception.UserNotFoundException;
import com.megamaker.codechallenge.user.domain.User;
import com.megamaker.codechallenge.user.domain.UserRepository;
import com.megamaker.codechallenge.user.domain.dto.RequestUserEdit;
import com.megamaker.codechallenge.badge.userbadge.domain.UserBadgeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserBadgeRepository userBadgeRepository;

    @Transactional
    public User get(String providerId) {
        return findUser(providerId);
    }



    @Transactional
    public void edit(String providerId, RequestUserEdit requestUserEdit) {
        User foundUser = findUser(providerId);
        foundUser = foundUser.updateNickname(requestUserEdit.getNickname());
        userRepository.save(foundUser);
    }

    public List<User> getRank() {
        return userRepository.findTopNByOrderByScoreDesc(10);
    }

    private User findUser(String providerId) {
        return userRepository.findByProviderId(providerId)
                .orElseThrow(UserNotFoundException::new);
    }
}
