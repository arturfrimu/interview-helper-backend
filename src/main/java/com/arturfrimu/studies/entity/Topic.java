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
import org.hibernate.Hibernate;

import java.util.Objects;

import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REFRESH;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "topics")
@NoArgsConstructor
@Getter
@Setter
public class Topic {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long topicId;

    private String name;

    private String description;

    @ManyToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH})
    @JoinColumn(name = "project_project_id")
    private Project project;

    public Topic(String name, String description, Project project) {
        this.name = name;
        this.description = description;
        this.project = project;
    }

    public Topic(Long topicId, String name, String description, Project project) {
        this(name, description, project);
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Topic topic = (Topic) o;
        return topicId != null && Objects.equals(topicId, topic.topicId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
