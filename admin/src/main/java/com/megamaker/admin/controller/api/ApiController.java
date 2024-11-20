package com.megamaker.admin.controller.api;

import com.megamaker.admin.dto.user.RequestJoin;
import com.megamaker.admin.dto.RequestProblem;
import com.megamaker.admin.service.ProblemService;
import com.megamaker.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ApiController {
    private final ProblemService problemService;
    private final UserService userService;

    @PostMapping("/problem")
    public ResponseEntity save(@RequestBody RequestProblem requestProblem) {
        problemService.save(requestProblem);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user")
    public void join(@RequestBody RequestJoin requestJoin) {
        userService.join(requestJoin);
    }
}