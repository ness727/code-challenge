package com.megamaker.codechallenge.entity;

import com.megamaker.codechallenge.domain.problem.Level;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "problems")
@Entity
public class Problem extends BaseTimeDate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated
    private Level level;

    private String description;

    private String limitation;

    @Column(name = "input_output")
    private String inputOutput;

    @Column(name = "solved_count")
    private Long solvedCount;

    @Column(name = "try_count")
    private Long tryCount;

    @Column(name = "correct_rate")
    private Float correctRate;

    @OneToMany(mappedBy = "problem")
    private Set<ProblemPicture> problemPictureList;

    @OneToMany(mappedBy = "problem")
    private Set<Testcase> testcaseList;
}
