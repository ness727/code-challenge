package com.megamaker.admin.controller;

import com.megamaker.admin.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/problem")
@RequiredArgsConstructor
@Controller
public class ProblemController {
    private final ProblemService problemService;

    @PostMapping
    public void save() {
        problemService.save();
    }
}