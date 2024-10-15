package com.megamaker.codechallenge.entity;

import com.megamaker.codechallenge.domain.problem.TestcaseDataType;
import com.megamaker.codechallenge.domain.problem.TestcaseType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "testcases")
@Entity
public class Testcase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private TestcaseDataType dataType; // content의 데이터 타입 ex) 0: int ...

    private TestcaseType type; // 0: 값 타입, 1: 배열 ...

    @ManyToOne
    @JoinColumn(name = "problem_id")
    public Problem problem;
}
