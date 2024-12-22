package com.megamaker.codechallenge.problem.presentation;

import com.megamaker.codechallenge.problem.exception.UserNotFoundException;
import com.megamaker.codechallenge.problem.domain.dto.ProblemSearchCond;
import com.megamaker.codechallenge.problem.domain.dto.ResponseListProblem;
import com.megamaker.codechallenge.problem.domain.dto.ResponseProblem;
import com.megamaker.codechallenge.problem.application.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public Page<ResponseListProblem> getList(
            @ModelAttribute ProblemSearchCond problemSearchCond,
            @PageableDefault(sort = "title", direction = ASC) Pageable pageable) {
        return problemService.getList(problemSearchCond, pageable);
    }

    @GetMapping("/{problemId}")
    public ResponseProblem get(@PathVariable Long problemId, Authentication auth) {
        return problemService.findById(problemId, authToProviderId(auth));
    }

    private static String authToProviderId(Authentication auth) {
        if (auth == null) throw new UserNotFoundException();
        return (String) auth.getPrincipal();
    }
}
