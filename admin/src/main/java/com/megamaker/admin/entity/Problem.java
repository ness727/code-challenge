package com.megamaker.admin.entity;

import com.megamaker.admin.domain.Level;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DynamicInsert
@Setter
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

    private Byte score;

    private String params;

    @Column(name = "return_type")
    private String returnType;

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
    private List<ProblemPicture> problemPictureList;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.PERSIST)
    private List<Testcase> testcaseList;
}
