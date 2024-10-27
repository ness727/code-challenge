package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.domain.problem.TypeConverter;
import com.megamaker.codechallenge.dto.RequestUserAnswer;
import com.megamaker.codechallenge.entity.Problem;
import com.megamaker.codechallenge.repository.ProblemRepository;
import com.megamaker.codechallenge.service.exception.UserClassFormatException;
import com.megamaker.codechallenge.service.exception.UserClassLoadException;
import com.megamaker.codechallenge.service.exception.UserCodeRuntimeException;
import com.megamaker.codechallenge.service.exception.UserMethodLoadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

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

    private final InternalMethod internalMethod;
    private final ProblemRepository problemRepository;

    @Override
    public String run(RequestUserAnswer requestUserAnswer) {
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
                throw new UserClassLoadException();
            } finally {
                deleteFiles(tempJavaFile, null);  // .java 삭제
            }

            // 인스턴스 생성 및 메서드 호출
            try {
                Object instance = loadedClass.getDeclaredConstructor().newInstance();

                // 해당 문제의 파라미터 타입 가져옴
                String[] splitParams = foundProblem.getParams().split(",");
                Class<?>[] paramClasses = TypeConverter.convertAll(splitParams);
                Method method = loadedClass.getMethod(METHOD, paramClasses);

                // 사용자 메서드 실행
                return internalMethod.runUserMethod(instance, method, requestUserAnswer);  // 메인 로직 메서드 실행
            } catch (NoSuchMethodException | SecurityException e) {
                throw new UserMethodLoadException();  // 메서드명 다를 때
            } catch (Exception e) {
                throw new UserCodeRuntimeException();  // 유저 코드 런타임 예외
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
}
