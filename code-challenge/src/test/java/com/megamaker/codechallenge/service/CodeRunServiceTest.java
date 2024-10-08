package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.exception.UserClassFormatException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class CodeRunServiceTest {
    //@Autowired CodeRunService codeRunService;
    String className = "Solution";
    String sourceCode =
            "public class " + className + " { " +
                "public void sayHello() { " +
                    "System.out.println(\"Hello!!!\"); " +
                "} " +
            "}";


//    @Test
//    void printTest() {
//        try {
//            codeRunService.run(sourceCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @DisplayName("잘못된 클래스명을 작성했을 때 예외 발생")
//    void invalidClassName() {
//        // given
//        String newClassName = "Invalid";
//        String sourceCode =
//                "public class " + newClassName + " { " +
//                        "public void sayHello() { " +
//                        "System.out.println(\"Hello!!!\"); " +
//                        "} " +
//                        "}";
//
//        // when
//        // then
//        Assertions.assertThatThrownBy(() -> codeRunService.run(sourceCode))
//                .isInstanceOf(UserClassFormatException.class);
//    }
}