package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.dto.user.RequestUserEdit;
import com.megamaker.codechallenge.dto.user.ResponseUser;
import com.megamaker.codechallenge.domain.entity.User;
import com.megamaker.codechallenge.mapper.UserMapper;
import com.megamaker.codechallenge.repository.UserRepository;
import com.megamaker.codechallenge.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseUser get(String providerId) {
        User foundUser = findUser(providerId);
        log.info("{}", foundUser.getNickname());
        return userMapper.toResponseUser(foundUser);
    }

    public void edit(String providerId, RequestUserEdit requestUserEdit) {
        User foundUser = findUser(providerId);
        foundUser.updateNickname(requestUserEdit.getNickname());
    }

    private User findUser(String providerId) {
        return userRepository.findByProviderId(providerId)
                .orElseThrow(UserNotFoundException::new);
    }
}
