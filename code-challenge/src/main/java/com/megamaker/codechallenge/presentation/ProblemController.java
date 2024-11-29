package com.megamaker.codechallenge.presentation;

import com.megamaker.codechallenge.application.exception.UserNotFoundException;
import com.megamaker.codechallenge.domain.problem.dto.ProblemSearchCond;
import com.megamaker.codechallenge.domain.problem.dto.ResponseListProblem;
import com.megamaker.codechallenge.domain.problem.dto.ResponseProblem;
import com.megamaker.codechallenge.application.ProblemService;
import com.megamaker.codechallenge.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public List<ResponseListProblem> getList(
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
