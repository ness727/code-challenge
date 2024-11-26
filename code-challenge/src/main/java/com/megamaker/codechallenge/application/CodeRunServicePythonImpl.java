package com.megamaker.codechallenge.application;

import com.megamaker.codechallenge.domain.coderun.dto.RequestUserAnswer;
import com.megamaker.codechallenge.domain.coderun.dto.ResponseUserCodeResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CodeRunServicePythonImpl implements CodeRunService {
    @Override
    public List<ResponseUserCodeResult> run(RequestUserAnswer requestUserAnswer) {
        log.info("python 코드 실행");
        return null;
    }
}
