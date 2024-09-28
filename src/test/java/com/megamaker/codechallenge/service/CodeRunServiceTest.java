package com.megamaker.codechallenge.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeRunServiceTest {

    @Test
    void printTest() {
        CodeRunService codeRunService = new CodeRunService();
        try {
            codeRunService.logic();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}