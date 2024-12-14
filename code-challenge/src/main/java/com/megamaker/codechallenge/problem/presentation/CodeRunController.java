package com.megamaker.codechallenge.problem.presentation;

import com.megamaker.codechallenge.problem.exception.UserRequestLangException;
import com.megamaker.codechallenge.problem.domain.dto.coderun.RequestUserAnswer;
import com.megamaker.codechallenge.problem.domain.dto.coderun.ResponseUserCodeResult;
import com.megamaker.codechallenge.problem.application.CodeRunService;
import com.megamaker.codechallenge.problem.application.CodeRunServiceJavaImpl;
import com.megamaker.codechallenge.problem.application.CodeRunServicePythonImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/code")
@RestController
public class CodeRunController {
    private final Map<String, CodeRunService> codeRunServiceMap;

    public CodeRunController(CodeRunServiceJavaImpl codeRunServiceJava,
                             CodeRunServicePythonImpl codeRunServicePython) {
        codeRunServiceMap = Map.of(
                "java", codeRunServiceJava,
                "python", codeRunServicePython
        );
    }

    @PostMapping("/answer")
    public List<ResponseUserCodeResult> checkAnswer(@RequestBody RequestUserAnswer requestUserAnswer) {
        String lang = requestUserAnswer.getLang().toLowerCase();

        if (codeRunServiceMap.containsKey(lang)) {
            CodeRunService codeRunService = codeRunServiceMap.get(lang);
            return codeRunService.run(requestUserAnswer);
        } else throw new UserRequestLangException();
    }
}
