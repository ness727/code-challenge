package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.dto.RequestUserAnswer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CodeRunServicePythonImpl implements CodeRunService {
    @Override
    public String run(RequestUserAnswer requestUserAnswer) {
        log.info("python 코드 실행");
        return null;
    }
}
