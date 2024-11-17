package com.megamaker.admin.service;

import com.megamaker.admin.domain.Role;
import com.megamaker.admin.dto.RequestJoin;
import com.megamaker.admin.entity.User;
import com.megamaker.admin.repository.UserRepository;
import com.megamaker.admin.securityconfig.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
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
}
