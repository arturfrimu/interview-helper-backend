package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateQuizCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateQuizCommand;
import com.arturfrimu.studies.dto.response.Response.QuizInfoResponse;
import com.arturfrimu.studies.entity.Quiz;
import com.arturfrimu.studies.exception.ExceptionContainer.ResourceNotFoundException;
import com.arturfrimu.studies.repository.LessonRepository;
import com.arturfrimu.studies.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;
import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final LessonRepository lessonRepository;

    public List<QuizInfoResponse> list() {
        return quizRepository.findAll().stream().map(QuizInfoResponse::valueOf).toList();
    }

    public QuizInfoResponse find(Long id) {
        var existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Quiz not found with id: %s", id)));

        return QuizInfoResponse.valueOf(existingQuiz);
    }

    public QuizInfoResponse create(CreateQuizCommand command) {
        var existingLesson = lessonRepository.findById(command.lessonId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));

        var newQuiz = new Quiz(command.name(), command.description(), existingLesson);

        return of(quizRepository.save(newQuiz)).map(QuizInfoResponse::valueOf)
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));
    }

    public QuizInfoResponse update(Long id, UpdateQuizCommand command) {
        var existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Quiz not found with id: %s", id)));

        var existingLesson = lessonRepository.findById(command.lessonId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));

        existingQuiz.setName(command.name());
        existingQuiz.setDescription(command.description());
        existingQuiz.setLesson(existingLesson);

        return of(quizRepository.save(existingQuiz)).map(QuizInfoResponse::valueOf)
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));
    }

    public void delete(Long id) {
        var existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Quiz not found with id: %s", id)));

        quizRepository.delete(existingQuiz);
    }
}
