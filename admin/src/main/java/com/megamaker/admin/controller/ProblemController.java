package com.megamaker.admin.controller;

import com.megamaker.admin.domain.Level;
import com.megamaker.admin.dto.problem.*;
import com.megamaker.admin.service.ProblemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/problem")
@Controller
public class ProblemController {
    private final ProblemService problemService;

    @GetMapping("/list")
    public String list(@ModelAttribute ProblemSearchCond problemSearchCond,
                       @PageableDefault(sort = "id", direction = ASC) Pageable pageable,
                       Model model) {
        Page<ResponseListProblem> foundProblemList = problemService.findAll(problemSearchCond, pageable);
        model.addAttribute("problemPage", foundProblemList);
        return "problem/list";
    }

    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        ResponseProblem foundProblem = problemService.findById(id);
        model.addAttribute("problem", foundProblem);
        model.addAttribute("levels", Level.values());
        return "problem/edit-form";
    }

    @PostMapping
    public String update(@ModelAttribute RequestProblemUpdate requestProblemUpdate, Model model,
                         HttpServletRequest request) {
        ResponseProblem updatedProblem = problemService.update(requestProblemUpdate);
        model.addAttribute("problem", updatedProblem);
        return "redirect:problem/" + updatedProblem.getId() + "?" + request.getQueryString();
    }
}
