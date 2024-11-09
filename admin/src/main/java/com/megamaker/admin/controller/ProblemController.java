package com.megamaker.admin.controller;

import com.megamaker.admin.dto.RequestProblem;
import com.megamaker.admin.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/problem")
@RequiredArgsConstructor
@RestController
public class ProblemController {
    private final ProblemService problemService;

    @PostMapping
    public ResponseEntity save(@RequestBody RequestProblem requestProblem) {
        problemService.save(requestProblem);
        return ResponseEntity.noContent().build();
    }
}