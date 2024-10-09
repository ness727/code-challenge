package com.megamaker.codechallenge.controller;

import com.megamaker.codechallenge.dto.RequestUserAnswer;
import com.megamaker.codechallenge.service.CodeRunService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/code")
@RequiredArgsConstructor
@RestController
public class CodeRunController {
    private final CodeRunService codeRunService;

    @PostMapping("/answer")
    public String checkAnswer(@RequestBody RequestUserAnswer requestUserAnswer) {
        //codeRunService.run();
        return null;
    }
}
