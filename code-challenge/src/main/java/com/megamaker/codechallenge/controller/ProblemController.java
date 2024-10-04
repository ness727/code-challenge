package com.megamaker.codechallenge.controller;

import com.megamaker.codechallenge.dto.ProblemSearchCond;
import com.megamaker.codechallenge.dto.ResponseProblem;
import com.megamaker.codechallenge.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * -------- 조회 예시 --------
 * ㅁ Pageable ㅁ
 * page=1
 * size=10
 * sort=title,asc
 * ㅁ ProblemSearchCond ㅁ
 * title=aaa
 * level=2
 */

@RequiredArgsConstructor
@RequestMapping("/problem")
@RestController
public class ProblemController {
    private final ProblemService problemService;

    @GetMapping("/list")
    public List<ResponseProblem> getList(
            @ModelAttribute ProblemSearchCond problemSearchCond,
            @PageableDefault(sort = "title", direction = ASC) Pageable pageable) {
        return problemService.getList(problemSearchCond, pageable);
    }
}
