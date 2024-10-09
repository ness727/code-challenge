package com.megamaker.codechallenge.entity;

import com.megamaker.codechallenge.domain.Level;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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

    @OneToMany(mappedBy = "problem")
    private Set<ProblemPicture> problemPictureList;

    @OneToMany(mappedBy = "problem")
    private Set<Testcase> testcaseList;
}
