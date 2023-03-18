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
@Table(name = "sections")
@NoArgsConstructor
@Getter
@Setter
public class Section {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long sectionId;

    private String name;

    private String description;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    public Section(String name, String description, Course course, Chapter chapter) {
        this.name = name;
        this.description = description;
        this.course = course;
        this.chapter = chapter;
    }
}
