package com.megamaker.codechallenge.problem.application;

import com.megamaker.codechallenge.problem.domain.dto.coderun.RequestUserAnswer;
import com.megamaker.codechallenge.problem.domain.dto.coderun.ResponseUserCodeResult;

import java.util.List;

public interface CodeRunService {
    List<ResponseUserCodeResult> run(RequestUserAnswer requestUserAnswer);
}
