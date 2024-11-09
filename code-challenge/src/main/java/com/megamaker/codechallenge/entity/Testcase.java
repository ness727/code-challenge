package com.megamaker.codechallenge.entity;

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

    @Column(name = "param_data")
    private String paramData;

    private String result;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    public Problem problem;
}
