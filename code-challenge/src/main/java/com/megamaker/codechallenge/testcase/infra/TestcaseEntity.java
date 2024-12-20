package com.megamaker.codechallenge.testcase.infra;

import com.megamaker.codechallenge.testcase.domain.Testcase;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "testcases")
@Entity
//@Embeddable
public class TestcaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "problem_id")
    private Long problemId;

    @Column(name = "param_data")
    private String paramData;

    private String result;

    public Testcase toModel() {
        return new Testcase(id, problemId, paramData, result);
    }

    public static TestcaseEntity from(Testcase testcase) {
        return new TestcaseEntity(testcase.getId(), testcase.getProblemId(), testcase.getParamData(), testcase.getResult());
    }
}