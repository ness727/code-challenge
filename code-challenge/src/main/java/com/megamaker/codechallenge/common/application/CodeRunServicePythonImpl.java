package com.megamaker.codechallenge.common.application;

import com.megamaker.codechallenge.problem.domain.dto.coderun.RequestUserAnswer;
import com.megamaker.codechallenge.problem.domain.dto.coderun.ResponseUserCodeResult;
import com.megamaker.codechallenge.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CodeRunServicePythonImpl implements CodeRunService {
    @Override
    public List<ResponseUserCodeResult> run(RequestUserAnswer requestUserAnswer, User user) {
        log.info("python 코드 실행");
        return null;
    }
}
