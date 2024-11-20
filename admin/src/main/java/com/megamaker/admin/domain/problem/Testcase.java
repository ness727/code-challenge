package com.megamaker.admin.domain.problem;

import jakarta.persistence.*;
import lombok.*;

@Setter
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