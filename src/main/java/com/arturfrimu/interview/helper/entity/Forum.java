package com.arturfrimu.interview.helper.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "forums")
@NoArgsConstructor
@Getter
@Setter
public class Forum {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long forumId;

    private String name;

    private String description;

    public Forum(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
