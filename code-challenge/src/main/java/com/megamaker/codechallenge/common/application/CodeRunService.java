package com.megamaker.codechallenge.common.application;

import com.megamaker.codechallenge.problem.domain.dto.coderun.RequestUserAnswer;
import com.megamaker.codechallenge.problem.domain.dto.coderun.ResponseUserCodeResult;
import com.megamaker.codechallenge.user.domain.User;

import java.util.List;

public interface CodeRunService {
    List<ResponseUserCodeResult> run(RequestUserAnswer requestUserAnswer, User user);
}
