package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateQuizCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateQuizCommand;
import com.arturfrimu.studies.entity.Quiz;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.LessonRepository;
import com.arturfrimu.studies.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final LessonRepository lessonRepository;

    public List<Quiz> list() {
        return quizRepository.findAll();
    }

    public Quiz find(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Quiz not found with id: %s", id)));
    }

    public Quiz create(CreateQuizCommand command) {
        var existingLesson = lessonRepository.findById(command.lessonId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));

        var newQuiz = new Quiz(command.name(), command.description(), existingLesson);

        return quizRepository.save(newQuiz);
    }

    public Quiz update(Long id, UpdateQuizCommand command) {
        var existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Quiz not found with id: %s", id)));

        var existingLesson = lessonRepository.findById(command.lessonId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));

        existingQuiz.setName(command.name());
        existingQuiz.setDescription(command.description());
        existingQuiz.setLesson(existingLesson);

        return quizRepository.save(existingQuiz);
    }

    public void delete(Long id) {
        var existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Quiz not found with id: %s", id)));

        quizRepository.delete(existingQuiz);
    }

}
