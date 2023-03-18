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
@Table(name = "progress")
@NoArgsConstructor
@Getter
@Setter
public class Progress {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long progressId;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private int progress;
}
