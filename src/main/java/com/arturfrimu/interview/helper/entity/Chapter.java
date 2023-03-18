package com.arturfrimu.interview.helper.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "chapters")
@NoArgsConstructor
@Getter
@Setter
public class Chapter {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long chapterId;

    private String name;

    private String description;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Chapter(String name, String description, Course course) {
        this.name = name;
        this.description = description;
        this.course = course;
    }
}
