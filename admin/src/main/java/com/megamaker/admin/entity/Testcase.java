package com.megamaker.admin.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "testcases")
@Entity
public class Testcase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String result;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    public Problem problem;
}