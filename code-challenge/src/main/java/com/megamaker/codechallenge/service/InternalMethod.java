package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.domain.problem.JavaTypeClazz;
import com.megamaker.codechallenge.dto.RequestUserAnswer;
import com.megamaker.codechallenge.entity.Testcase;
import com.megamaker.codechallenge.repository.TestcaseRepository;
import com.megamaker.codechallenge.service.exception.UserCodeRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class InternalMethod {
    private final TestcaseRepository testcaseRepository;

    public String runUserMethod(Object instance, Method method,
                                RequestUserAnswer requestUserAnswer) throws IllegalAccessException, InvocationTargetException {
        List<Testcase> testcaseList = testcaseRepository.findByProblemId(requestUserAnswer.getProblemId());

        for (Testcase testCase : testcaseList) {
            String[] paramArr = testCase.getContent().split("/");  // 파라미터 개수만큼 나누기
            Object[] result = new Object[paramArr.length];

            for (int i = 0 ; i < paramArr.length; i++) {
                Class<?> paramType = method.getParameterTypes()[i];  // 파라미터 타입
                String[] paramDataArr = paramArr[i].split(",");

                Object convResult;
                if (paramType.isArray()) {  // 배열일 때
                    // 배열 내의 타입 구하기
                    Class<?> componentType = paramType.getComponentType();
                    convResult = convert(null, paramDataArr, componentType);
                } else {  // 배열 아닐 때
                    convResult = convert(paramDataArr[0], null, paramType);
                }

                result[i] = convResult;
            }

            method.invoke(instance, result);
        }
        return null;
    }

    /**
     * 배열 또는 단일 값일 때, String 값을 해당 class 타입으로 변경
     */
    private static Object convert(String single, String[] arr, Class<?> targetClazz) {
        try {
            Method convMethod = JavaTypeClazz.toMethod(targetClazz);

            // 배열일 때
            if (arr != null) {
                Object newArray = Array.newInstance(targetClazz, arr.length);

                for (int i = 0; i < arr.length; i++) Array.set(newArray, i, convMethod.invoke(null, arr[i]));
                return newArray;
            }
            // 단일 값일 때
            else if (single != null) return convMethod.invoke(null, single);
            else return null;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new UserCodeRuntimeException(e);
        }
    }
}
