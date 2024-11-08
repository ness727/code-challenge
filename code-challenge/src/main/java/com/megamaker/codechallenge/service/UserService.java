package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.dto.user.RequestUserEdit;
import com.megamaker.codechallenge.entity.User;
import com.megamaker.codechallenge.repository.UserRepository;
import com.megamaker.codechallenge.service.exception.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void edit(RequestUserEdit requestUserEdit) {
        User foundUser = userRepository.findByProviderId(requestUserEdit.getProviderId())
                .orElseThrow(UserNotFoundException::new);
        foundUser.setNickname(requestUserEdit.getNickname());
    }
}
