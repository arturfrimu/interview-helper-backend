package com.arturfrimu.studies.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "lessons")
@NoArgsConstructor
@Getter
@Setter
public class Lesson {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long lessonId;

    private String name;

    private String description;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany(mappedBy = "lesson", cascade = ALL, fetch = LAZY)
    private Set<Quiz> quizzes = new LinkedHashSet<>();

    public Lesson(String name, String description, Topic topic) {
        this.name = name;
        this.description = description;
        this.topic = topic;
    }
}
