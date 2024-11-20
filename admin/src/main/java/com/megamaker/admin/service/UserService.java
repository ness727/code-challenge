package com.megamaker.admin.service;

import com.megamaker.admin.domain.Role;
import com.megamaker.admin.dto.problem.ResponseListProblem;
import com.megamaker.admin.dto.user.RequestJoin;
import com.megamaker.admin.dto.user.ResponseListUser;
import com.megamaker.admin.dto.user.ResponseUser;
import com.megamaker.admin.dto.user.UserSearchCond;
import com.megamaker.admin.entity.User;
import com.megamaker.admin.mapper.UserMapper;
import com.megamaker.admin.repository.UserRepository;
import com.megamaker.admin.securityconfig.Provider;
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
//
//    @Transactional
//    public ResponseProblem update(RequestProblemUpdate requestProblemUpdate) {
//        Problem foundProblem = problemRepository.findById(requestProblemUpdate.getId())
//                .orElseThrow(() -> new EmptyResultDataAccessException(1));
//        foundProblem.update(requestProblemUpdate);
//        return problemMapper.toResponseProblem(foundProblem);
//    }
//
//    @Transactional
//    public void remove(Long id) {
//        problemRepository.remove(id);
//    }

}
