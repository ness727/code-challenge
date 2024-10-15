package com.megamaker.admin.repository;

import com.megamaker.admin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
