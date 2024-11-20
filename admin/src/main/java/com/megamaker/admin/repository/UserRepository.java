package com.megamaker.admin.repository;

import com.megamaker.admin.domain.Role;
import com.megamaker.admin.dto.problem.ProblemSearchCond;
import com.megamaker.admin.dto.user.UserSearchCond;
import com.megamaker.admin.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByProviderIdAndRole(String providerId, Role role);
    void save(User user);
    Page<User> findAll(UserSearchCond userSearchCond, Pageable pageable);
    Optional<User> findById(Long id);
}
