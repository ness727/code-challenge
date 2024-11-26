package com.megamaker.admin.domain.user;

import com.megamaker.admin.domain.user.dto.UserSearchCond;
import com.megamaker.admin.domain.user.vo.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByProviderIdAndRole(String providerId, Role role);

    void save(User user);

    Page<User> findAll(UserSearchCond userSearchCond, Pageable pageable);

    Optional<User> findById(Long id);

    void removeById(Long id);
}
