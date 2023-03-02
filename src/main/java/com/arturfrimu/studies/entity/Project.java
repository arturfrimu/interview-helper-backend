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

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long projectId;

    private String name;

    private String description;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "section_id")
    private Section section;

    public Project(String name, String description, Course course, Section section) {
        this.name = name;
        this.description = description;
        this.course = course;
        this.section = section;
    }
}
