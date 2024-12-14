package com.megamaker.codechallenge.user.application;

import com.megamaker.codechallenge.user.domain.vo.Role;
import com.megamaker.codechallenge.user.domain.dto.RequestUserEdit;
import com.megamaker.codechallenge.user.domain.dto.ResponseUser;
import com.megamaker.codechallenge.user.domain.User;
import com.megamaker.codechallenge.user.domain.dto.ResponseUserRank;
import com.megamaker.codechallenge.user.mapper.UserMapper;
import com.megamaker.codechallenge.user.domain.UserRepository;
import com.megamaker.codechallenge.problem.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseUser get(String providerId) {
        User foundUser = findUser(providerId);
        return userMapper.toResponseUser(foundUser);
    }

    public void edit(String providerId, RequestUserEdit requestUserEdit) {
        User foundUser = findUser(providerId);
        foundUser.updateNickname(requestUserEdit.getNickname());
    }

    public List<ResponseUserRank> getRank() {
        List<User> foundUserList = userRepository.findTopNByOrderByScoreDesc(10);
        return foundUserList.stream()
                .filter(user ->
                    user.getRole() != Role.ADMIN
                        && user.getRole() != Role.MANAGER
                )
                .map(user -> {
                    if (user.getNickname() == null) {  // 닉네임 설정 안 되어 있을 때
                        return new ResponseUserRank(user.getProvider().getProviderNickname() + "(GitHub)", user.getScore());
                    } else return userMapper.toResponseUserRank(user);
                })
                .toList();
    }

    private User findUser(String providerId) {
        return userRepository.findByProviderProviderId(providerId)
                .orElseThrow(UserNotFoundException::new);
    }
}
