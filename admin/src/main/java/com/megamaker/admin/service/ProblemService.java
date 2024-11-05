package com.megamaker.admin.service;

import com.megamaker.admin.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProblemService {
    private final ProblemRepository problemRepository;

    public void save() {
//        problemRepository.save();
    }
}