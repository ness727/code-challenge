package com.megamaker.codechallenge.service;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class CodeRunService {
    String className = "Hello";
    String sourceCode =
            "public class " + className + " { " +
                "public void sayHello() { " +
                    "System.out.println(\"Hello!!!\"); " +
                "} " +
            "}";

    public void logic() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        // 컴파일러 인스턴스 얻기
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        // 소스 파일 생성
        File tempSourceFile = new File("./" , className + ".java");
        try (Writer writer = new BufferedWriter(new FileWriter(tempSourceFile))) {
            writer.write(sourceCode);
        }

        // 파일 컴파일
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(tempSourceFile);
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();

        // 클래스 로드
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{tempSourceFile.getParentFile().toURI().toURL()});
        Class<?> loadedClass = Class.forName(className, true, classLoader);

        // 인스턴스 생성 및 메서드 호출
        Object instance = loadedClass.getDeclaredConstructor().newInstance();
        Method method = loadedClass.getMethod("sayHello");

        // 메인 로직 메서드 실행
        method.invoke(instance);

        // .java 파일 삭제
        tempSourceFile.delete();
        
        // .class 파일 삭제
        File classFile = new File("./", className + ".class");
        classFile.delete();

        fileManager.close();
    }
}
