package com.megamaker.codechallenge.entity;

import com.megamaker.codechallenge.domain.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Problem extends BaseTimeDate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated
    private Level level;

    private String content;

    @ManyToOne
    @JoinColumn(name = "problem_picture_id")
    private ProblemPicture problemPicture;
}
