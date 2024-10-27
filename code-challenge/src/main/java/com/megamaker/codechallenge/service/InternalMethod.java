package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.dto.RequestUserAnswer;
import com.megamaker.codechallenge.repository.TestcaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
@Component
public class InternalMethod {
    private final TestcaseRepository testcaseRepository;

    public String runUserMethod(Object instance, Method method,
                                RequestUserAnswer requestUserAnswer) throws IllegalAccessException, InvocationTargetException {
//        List<Testcase> testcaseList = testcaseRepository.findByProblemId(requestUserAnswer.getProblemId());
//        for (Testcase testCase : testcaseList) {
//            method.getParameterTypes()
//            method.invoke(instance, );
//        }
        log.info("method params = {}", method.getParameterTypes());
        method.invoke(instance, "s", new int[] {1,2});
        return null;
    }
}
