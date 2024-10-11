package com.megamaker.admin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "date_type")
    private String dateType;

    @Column(name = "type", nullable = false)
    private String type;
}