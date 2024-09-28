package com.megamaker.codechallenge;

import com.megamaker.codechallenge.service.CodeRunService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class CodechallengeApplication {

	public static void main(String[] args) {
        try {
            new CodeRunService().logic();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        SpringApplication.run(CodechallengeApplication.class, args);
	}

}
