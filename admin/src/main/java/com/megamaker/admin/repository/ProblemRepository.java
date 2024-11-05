package com.megamaker.admin.repository;

import com.megamaker.admin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<User, Long> {
}
