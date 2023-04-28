package com.arturfrimu.interview.helper.entity;

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
import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REFRESH;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long courseId;

    private String name;

    private String description;

    @ManyToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH})
    @JoinColumn(name = "topic_topic_id")
    private Topic topic;

    @OneToMany(mappedBy = "course", cascade = ALL, fetch = LAZY)
    private Set<Section> sections = new LinkedHashSet<>();

    public Course(String name, String description, Topic topic) {
        this.name = name;
        this.description = description;
        this.topic = topic;
    }

    public Course(Long courseId, String name, String description, Topic topic) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.topic = topic;
    }
}
