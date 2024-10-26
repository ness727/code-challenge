package com.megamaker.codechallenge.controller;

import com.megamaker.codechallenge.controller.exception.UserRequestLangException;
import com.megamaker.codechallenge.dto.RequestUserAnswer;
import com.megamaker.codechallenge.service.CodeRunService;
import com.megamaker.codechallenge.service.CodeRunServiceJavaImpl;
import com.megamaker.codechallenge.service.CodeRunServicePythonImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequestMapping("/code")
@RestController
public class CodeRunController {
    private final Map<String, CodeRunService> codeRunServiceMap;

    public CodeRunController(CodeRunServiceJavaImpl codeRunServiceJava,
                             CodeRunServicePythonImpl codeRunServicePython) {
        codeRunServiceMap = new HashMap<>();
        codeRunServiceMap.put("java", codeRunServiceJava);
        codeRunServiceMap.put("python", codeRunServicePython);
    }

    @PostMapping("/answer")
    public String checkAnswer(@RequestBody RequestUserAnswer requestUserAnswer) {
        String lang = requestUserAnswer.getLang().toLowerCase();
        if (codeRunServiceMap.containsKey(lang)) {
            CodeRunService codeRunService = codeRunServiceMap.get(lang);
            codeRunService.run(requestUserAnswer);
        } else throw new UserRequestLangException();

        return "good";
    }
}
