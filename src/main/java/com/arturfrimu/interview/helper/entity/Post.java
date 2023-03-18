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
@Table(name = "posts")
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long postId;

    private String title;

    private String content;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "forum_id")
    private Forum forum;

    public Post(String title, String content, Forum forum) {
        this.title = title;
        this.content = content;
        this.forum = forum;
    }
}
