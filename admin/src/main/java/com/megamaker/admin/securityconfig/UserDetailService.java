package com.megamaker.admin.securityconfig;

import com.megamaker.admin.domain.Role;
import com.megamaker.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String providerId) throws UsernameNotFoundException {
        return userRepository.findByProviderIdAndRole(providerId, Role.ADMIN)
                .orElseThrow(() -> new UsernameNotFoundException("회원 로그인 실패"));
    }
}
