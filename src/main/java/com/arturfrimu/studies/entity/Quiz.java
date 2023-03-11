package com.arturfrimu.studies.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "quiz")
@NoArgsConstructor
@Getter
@Setter
public class Quiz {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long quizId;

    private String name;

    private String description;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    public Quiz(String name, String description, Lesson lesson) {
        this.name = name;
        this.description = description;
        this.lesson = lesson;
    }
}
