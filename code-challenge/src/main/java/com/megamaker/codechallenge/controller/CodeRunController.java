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
import java.util.List;

@Slf4j
@RequestMapping("/code")
@RequiredArgsConstructor
@RestController
public class CodeRunController {
    private final List<CodeRunService> codeRunServiceList;

    @PostMapping("/answer")
    public String checkAnswer(@RequestBody RequestUserAnswer requestUserAnswer) {
        CodeRunService codeRunService = Arrays.stream(Lang.values())
                .filter(lang -> lang.name().equals(requestUserAnswer.getLang().toUpperCase()))
                .map(Lang::getcodeRunImplClass)
                .map(codeRunImplClass ->
                        codeRunServiceList.stream()
                                .filter(codeRunImplClass::isInstance)
                                .findFirst()
                                .orElseThrow(UserRequestLangException::new)
                )
                .findAny()
                .orElseThrow(UserRequestLangException::new);

        codeRunService.run(requestUserAnswer);

        return "good";
    }

    private enum Lang {
        JAVA(CodeRunServiceJavaImpl.class),
        PYTHON(CodeRunServicePythonImpl.class);

        private final Class<? extends CodeRunService> codeRunImplClass;

        Lang(Class<? extends CodeRunService> codeRunImplClass) {
            this.codeRunImplClass = codeRunImplClass;
        }

        public Class<? extends CodeRunService> getcodeRunImplClass() {
            return codeRunImplClass;
        }
    }
}
