package com.megamaker.admin.controller;

import com.megamaker.admin.dto.RequestProblem;
import com.megamaker.admin.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/problem")
@RequiredArgsConstructor
@Controller
public class ProblemController {
    private final ProblemService problemService;

    @ResponseBody
    @PostMapping
    public ResponseEntity save(@RequestBody RequestProblem requestProblem) {
        problemService.save(requestProblem);
        return ResponseEntity.noContent().build();
    }

}