package com.megamaker.codechallenge.application;

import com.megamaker.codechallenge.domain.coderun.dto.RequestUserAnswer;
import com.megamaker.codechallenge.domain.coderun.dto.ResponseUserCodeResult;

import java.util.List;

public interface CodeRunService {
    List<ResponseUserCodeResult> run(RequestUserAnswer requestUserAnswer);
}
