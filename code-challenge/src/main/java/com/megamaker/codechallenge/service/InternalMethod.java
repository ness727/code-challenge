package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.dto.RequestUserAnswer;
import com.megamaker.codechallenge.repository.TestcaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiredArgsConstructor
@Component
public class InternalMethod {
    private final TestcaseRepository testcaseRepository;

    public String runUserMethod(Object instance, Method method,
                                RequestUserAnswer requestUserAnswer) throws IllegalAccessException, InvocationTargetException {
//        List<Testcase> testcaseList = testcaseRepository.findByProblemId(requestUserAnswer.getProblemId());
//        for (Testcase testCase : testcaseList) {
//            method.invoke(instance, );
//        }
        method.invoke(instance);
        return null;
    }
}
