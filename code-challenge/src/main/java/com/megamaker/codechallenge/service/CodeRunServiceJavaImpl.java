package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.domain.problem.JavaTypeClazz;
import com.megamaker.codechallenge.dto.RequestUserAnswer;
import com.megamaker.codechallenge.dto.ResponseUserCodeResult;
import com.megamaker.codechallenge.domain.entity.Problem;
import com.megamaker.codechallenge.domain.entity.Testcase;
import com.megamaker.codechallenge.domain.entity.User;
import com.megamaker.codechallenge.domain.entity.UserProblem;
import com.megamaker.codechallenge.repository.ProblemRepository;
import com.megamaker.codechallenge.repository.TestcaseRepository;
import com.megamaker.codechallenge.repository.UserProblemRepository;
import com.megamaker.codechallenge.repository.UserRepository;
import com.megamaker.codechallenge.securityconfig.oauth2.CustomOAuth2User;
import com.megamaker.codechallenge.service.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 답안의 클래스명은 Solution으로 고정
 * 답안의 메서드명은 main()으로 고정
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class CodeRunServiceJavaImpl implements CodeRunService {
    private static final String CLASS_SOLUTION = "class Solution";
    private static final String SOLUTION = "Solution";
    private static final String METHOD = "main";

    private final ProblemRepository problemRepository;
    private final TestcaseRepository testcaseRepository;
    private final UserRepository userRepository;
    private final BadgeService badgeService;
    private final UserProblemRepository userProblemRepository;

    @Transactional
    @Override
    public List<ResponseUserCodeResult> run(RequestUserAnswer requestUserAnswer) {
        String sourceCode = requestUserAnswer.getSourceCode();

        if (!sourceCode.contains(CLASS_SOLUTION)) throw new UserClassFormatException();

        Problem foundProblem = problemRepository.findById(requestUserAnswer.getProblemId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        // 컴파일러 인스턴스 얻기
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        try (StandardJavaFileManager fileManager = javac.getStandardFileManager(null, null, null)) {
            // 소스 파일 생성
            File tempJavaFile = File.createTempFile(SOLUTION, ".java");  // 결과 파일 예: Solution237529.java
            String newClassName;

            newClassName = tempJavaFile.getName().split("\\.")[0];

            // 클래스명 임시 파일명과 동일하게 변경
            try (Writer writer = new BufferedWriter(new FileWriter(tempJavaFile))) {
                sourceCode = sourceCode.replaceFirst(CLASS_SOLUTION, "class " + newClassName);
                writer.write(sourceCode);
            }

            // javac 컴파일
            Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(tempJavaFile);
            javac.getTask(null, fileManager, null, null, null, fileObjects).call();

            // 클래스 로드
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{tempJavaFile.getParentFile().toURI().toURL()});
            Class<?> loadedClass = null;
            try {
                loadedClass = Class.forName(newClassName, true, classLoader);
            } catch (ClassNotFoundException e) {
                throw new UserClassLoadException(e);
            } finally {
                deleteFiles(tempJavaFile, null);  // .java 삭제
            }

            // 인스턴스 생성 및 메서드 호출
            try {
                Object instance = loadedClass.getDeclaredConstructor().newInstance();

                // 해당 문제의 파라미터 타입 가져옴
                String[] splitParams = foundProblem.getParams().split(",");
                Class<?>[] paramClasses = JavaTypeClazz.convertAll(splitParams);
                Method method = loadedClass.getMethod(METHOD, paramClasses);

                // 사용자 메서드 실행
                return runUserMethod(instance, method, requestUserAnswer, foundProblem);  // 메인 로직 메서드 실행
            } catch (NoSuchMethodException | SecurityException e) {
                throw new UserMethodLoadException(e);  // 메서드명 다를 때
            } catch (Exception e) {
                throw new UserCodeRuntimeException(e);  // 유저 코드 런타임 예외
            } finally {
                deleteFiles(null, newClassName);  // .class 삭제
            }
        } catch (IOException e) {
            throw new RuntimeException("FileManager 오류");
        }
    }

    private void deleteFiles(File javaFile, String className) {
        // .java 파일 삭제
        if (javaFile != null) javaFile.delete();

        // .class 파일 삭제
        if (StringUtils.hasText(className)) {
            File classFile = new File(System.getProperty("java.io.tmpdir") + className + ".class");
            classFile.delete();
        }
    }

    public List<ResponseUserCodeResult> runUserMethod(Object instance, Method method,
                                                      RequestUserAnswer requestUserAnswer, Problem problem) throws IllegalAccessException, InvocationTargetException {
        List<Testcase> testcaseList = testcaseRepository.findByProblemId(requestUserAnswer.getProblemId());
        List<ResponseUserCodeResult> result = new ArrayList<>();

        for (Testcase testcase : testcaseList) {
            String[] paramArr = testcase.getParamData().split("/");  // 파라미터 개수만큼 나누기
            Object[] paramTypeResult = new Object[paramArr.length];

            for (int i = 0 ; i < paramArr.length; i++) {
                Class<?> paramType = method.getParameterTypes()[i];  // 파라미터 타입
                String[] paramDataArr = paramArr[i].split(",");

                Object convResult;
                if (paramType.isArray()) {  // 배열일 때
                    // 배열 내의 타입 구하기
                    Class<?> componentType = paramType.getComponentType();
                    convResult = convert(paramDataArr, componentType);
                } else {  // 배열 아닐 때
                    convResult = convert(paramDataArr[0], paramType);
                }

                paramTypeResult[i] = convResult;
            }

            long startTime = System.currentTimeMillis();
            Object userCodeReturn = method.invoke(instance, paramTypeResult);  // 유저 메서드 실행
            long endTime = System.currentTimeMillis() - startTime;

            boolean isCorrect = true;
            // 리턴 값이 배열일 때
            if (userCodeReturn.getClass().isArray()) {
                String[] userCodeReturnArr =
                        Arrays.stream((Object[]) userCodeReturn)
                                .map(String::valueOf)
                                .toArray(String[]::new);
                String[] testcaseReturnArr = testcase.getResult().split(",");

                isCorrect = Arrays.equals(userCodeReturnArr, testcaseReturnArr);
            } else {  // 리턴 값이 단일 값일 때
                isCorrect = testcase.getResult().equals(String.valueOf(userCodeReturn));
            }
            result.add(new ResponseUserCodeResult(endTime, testcase.getResult(), userCodeReturn, isCorrect));
        }
        
        SecurityContext context = SecurityContextHolder.getContext();
        CustomOAuth2User userAuth = (CustomOAuth2User) context.getAuthentication().getPrincipal();
        User foundUser = userRepository.findByProviderId(userAuth.getProviderId())
                .orElseThrow(UserNotFoundException::new);

        // 정답인 유저에게 문제 점수만큼 유저 점수 추가
        boolean isAllCorrect = result.stream()
                .filter(ResponseUserCodeResult::getIsCorrect)
                .toArray().length == result.size();
        if (isAllCorrect) {
            // 이미 풀었던 문제일 때
            Optional<UserProblem> foundUserProblem = userProblemRepository.findByUserIdAndProblemId(foundUser.getId(), problem.getId());
            if (foundUserProblem.isPresent()) {
                foundUserProblem.get().updateUserAnswer(requestUserAnswer.getSourceCode());
            } else {
                // UserProblem 엔티티 새로 추가
                UserProblem newUserProblem = new UserProblem(null, foundUser, problem, requestUserAnswer.getSourceCode());
                userProblemRepository.save(newUserProblem);

                foundUser.addScoreAndSolveCount(problem.getScore());  // 유저 점수 추가
                problem.increaseCorrectAnswerCount();  // 문제 정답자 카운트 증가

                badgeService.correctCondCheck(foundUser); // 정답 시 뱃지 획득 조건 검사
            }
        } else problem.increaseWrongAnswerCount();  // 문제 시도자 카운트 증가

        return result;
    }

    /**
     * 배열 또는 단일 값일 때, String 값을 해당 class 타입으로 변경
     */

    // 단일 값일 때
    private Object convert(String single, Class<?> targetClazz) {
        try {
            Method convMethod = JavaTypeClazz.toMethod(targetClazz);
            return convMethod.invoke(null, single);  // String을 해당 타입으로 변환
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new UserCodeRuntimeException(e);
        }
    }

    // 배열일 때
    private Object convert(String[] arr, Class<?> targetClazz) {
        try {
            Method convMethod = JavaTypeClazz.toMethod(targetClazz);
            Object newArray = Array.newInstance(targetClazz, arr.length);

            for (int i = 0; i < arr.length; i++) Array.set(newArray, i, convMethod.invoke(null, arr[i]));
            return newArray;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new UserCodeRuntimeException(e);
        }
    }
}
