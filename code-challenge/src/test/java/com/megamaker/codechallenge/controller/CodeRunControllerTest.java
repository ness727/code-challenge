package com.megamaker.codechallenge.controller;

import com.megamaker.codechallenge.service.CodeRunService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@Slf4j
@SpringBootTest
class CodeRunControllerTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    void checkCodeRunServiceBeans() {
        Map<String, CodeRunService> beansOfType = applicationContext.getBeansOfType(CodeRunService.class);

        for (Map.Entry<String, CodeRunService> entry : beansOfType.entrySet()) {
            log.info("beanName={} / Service={}", entry.getKey(), entry.getValue());
        }
    }
}