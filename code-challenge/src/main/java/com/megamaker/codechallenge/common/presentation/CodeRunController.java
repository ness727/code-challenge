package com.megamaker.codechallenge.common.presentation;

import com.megamaker.codechallenge.problem.exception.UserRequestLangException;
import com.megamaker.codechallenge.problem.domain.dto.coderun.RequestUserAnswer;
import com.megamaker.codechallenge.problem.domain.dto.coderun.ResponseUserCodeResult;
import com.megamaker.codechallenge.common.application.CodeRunService;
import com.megamaker.codechallenge.common.application.CodeRunServiceJavaImpl;
import com.megamaker.codechallenge.common.application.CodeRunServicePythonImpl;
import com.megamaker.codechallenge.user.domain.User;
import com.megamaker.codechallenge.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
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
    private final UserRepository userRepository;

    public CodeRunController(CodeRunServiceJavaImpl codeRunServiceJava,
                             CodeRunServicePythonImpl codeRunServicePython, UserRepository userRepository) {
        codeRunServiceMap = Map.of(
                "java", codeRunServiceJava,
                "python", codeRunServicePython
        );
        this.userRepository = userRepository;
    }

    @PostMapping("/answer")
    public List<ResponseUserCodeResult> checkAnswer(@RequestBody RequestUserAnswer requestUserAnswer, Authentication auth) {
        String lang = requestUserAnswer.getLang().toLowerCase();

        if (codeRunServiceMap.containsKey(lang)) {
            CodeRunService codeRunService = codeRunServiceMap.get(lang);
            return codeRunService.run(requestUserAnswer, getUser(auth));
        } else throw new UserRequestLangException();
    }

    private User getUser(Authentication auth) {
        String providerId = (String) auth.getPrincipal();
        return userRepository.findByProviderId(providerId).orElseThrow();
    }
}
