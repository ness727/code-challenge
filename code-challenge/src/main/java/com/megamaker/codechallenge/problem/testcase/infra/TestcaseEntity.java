package com.megamaker.codechallenge.problem.testcase.infra;

import com.megamaker.codechallenge.problem.infra.ProblemEntity;
import com.megamaker.codechallenge.problem.testcase.domain.Testcase;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "testcases")
@Entity
//@Embeddable
public class TestcaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "problem_id")
//    private Long problemId;

    @Column(name = "param_data")
    private String paramData;

    private String result;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private ProblemEntity problemEntity;

    public TestcaseEntity(Integer id, String paramData, String result) {
        this.id = id;
        this.paramData = paramData;
        this.result = result;
    }

    public Testcase toModel() {
        return new Testcase(id, paramData, result);
    }

    public static TestcaseEntity from(Testcase testcase) {
        return new TestcaseEntity(testcase.getId(), testcase.getParamData(), testcase.getResult());
    }
}