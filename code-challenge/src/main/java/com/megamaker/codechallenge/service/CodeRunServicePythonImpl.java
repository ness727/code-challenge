package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.dto.RequestUserAnswer;
import com.megamaker.codechallenge.dto.ResponseUserCodeResult;
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
