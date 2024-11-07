package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.dto.RequestUserAnswer;
import com.megamaker.codechallenge.dto.ResponseUserCodeResult;

import java.util.List;

public interface CodeRunService {
    List<ResponseUserCodeResult> run(RequestUserAnswer requestUserAnswer);
}
