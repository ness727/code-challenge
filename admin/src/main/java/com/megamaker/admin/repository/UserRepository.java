package com.megamaker.admin.repository;

import com.megamaker.admin.domain.Role;
import com.megamaker.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //@Query("select u from User u where u.providerId = :providerId and u.role = :role")
    Optional<User> findByProviderIdAndRole(String providerId, Role role);
}
