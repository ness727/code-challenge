package com.megamaker.admin.repository;

import com.megamaker.admin.entity.Problem;
import com.megamaker.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
