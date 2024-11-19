package com.megamaker.admin.entity;

import com.megamaker.admin.domain.Level;
import com.megamaker.admin.dto.problem.RequestProblemUpdate;
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

    public void update(RequestProblemUpdate request) {
        this.title = request.getTitle();
        this.level = request.getLevel();
        this.score = request.getScore();
        this.params = request.getParams();
        this.returnType = request.getReturnType();
        this.description = request.getDescription();
        this.limitation = request.getLimitation();
        this.inputOutput = request.getInputOutput();
    }
}
