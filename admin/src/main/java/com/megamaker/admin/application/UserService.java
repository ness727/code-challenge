package com.megamaker.admin.application;

import com.megamaker.admin.domain.user.Role;
import com.megamaker.admin.domain.user.dto.*;
import com.megamaker.admin.domain.user.User;
import com.megamaker.admin.domain.user.mapper.UserMapper;
import com.megamaker.admin.domain.user.UserRepository;
import com.megamaker.admin.config.security.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(RequestJoin requestJoin) {
        User newUser = User.builder()
                .provider(Provider.GITHUB)
                .providerId(requestJoin.getId())
                .providerNickname(passwordEncoder.encode(requestJoin.getPassword()))
                .nickname(requestJoin.getNickname())
                .role(Role.ADMIN)
                .build();
        userRepository.save(newUser);
    }

    public Page<ResponseListUser> findAll(UserSearchCond userSearchCond, Pageable pageable) {
        return userRepository.findAll(userSearchCond, pageable)
                .map(userMapper::toResponseListUser);
    }

    public ResponseUser findById(Long id) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return userMapper.toResponseUser(foundUser);
    }

    @Transactional
    public ResponseUser update(RequestUserUpdate requestUserUpdate) {
        User foundUser = userRepository.findById(requestUserUpdate.getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        foundUser.update(requestUserUpdate);
        return userMapper.toResponseUser(foundUser);
    }

    @Transactional
    public void remove(Long id) {
        userRepository.removeById(id);
    }

}
