package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.exception.UserClassFormatException;
import org.springframework.stereotype.Service;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 답안의 클래스명은 Solution으로 고정
 * 답안의 메서드명은 main()으로 고정
 */

@Service
public class CodeRunService {
    static final String CLASS_SOLUTION = "class Solution";
    static final String SOLUTION = "Solution";

    public void run(String sourceCode) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        // 컴파일러 인스턴스 얻기
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = javac.getStandardFileManager(null, null, null);

        // 소스 파일 생성
        File tempJavaFile = File.createTempFile(SOLUTION, ".java");  // 결과 파일 예: Solution237529.java
        String newClassName;

        if (!sourceCode.contains(CLASS_SOLUTION)) throw new UserClassFormatException("클래스 형식에 맞춰서 작성해주세요.");
        else newClassName = tempJavaFile.getName().split("\\.")[0];

        try (Writer writer = new BufferedWriter(new FileWriter(tempJavaFile))) {
            sourceCode = sourceCode.replaceFirst(CLASS_SOLUTION, "class " + newClassName);
            writer.write(sourceCode);
        }

        // 파일 컴파일
        Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(tempJavaFile);
        javac.getTask(null, fileManager, null, null, null, fileObjects).call();

        // 클래스 로드
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{tempJavaFile.getParentFile().toURI().toURL()});
        Class<?> loadedClass = Class.forName(newClassName, true, classLoader);

        // 인스턴스 생성 및 메서드 호출
        Object instance = loadedClass.getDeclaredConstructor().newInstance();
        Method method = loadedClass.getMethod("sayHello");

        // 메인 로직 메서드 실행
        method.invoke(instance);

        // .java 파일 삭제
        tempJavaFile.delete();
        
        // .class 파일 삭제
        File classFile = new File("./", newClassName + ".class");
        classFile.delete();

        fileManager.close();
    }
}
