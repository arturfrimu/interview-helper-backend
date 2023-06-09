package com.arturfrimu.interview.helper.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
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

    @OneToMany(mappedBy = "project", cascade = ALL, fetch = LAZY)
    private Set<Topic> topics = new LinkedHashSet<>();

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Project(Long projectId, String name, String description) {
        this(name, description);
        this.projectId = projectId;
    }
}
