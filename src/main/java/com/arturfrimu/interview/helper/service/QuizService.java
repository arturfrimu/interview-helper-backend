package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.entity.Quiz;
import com.arturfrimu.interview.helper.dto.response.Response.QuizInfoResponse;
import com.arturfrimu.interview.helper.exception.ExceptionContainer.ResourceNotFoundException;
import com.arturfrimu.interview.helper.repository.LessonRepository;
import com.arturfrimu.interview.helper.repository.QuizRepository;
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
        return quizRepository.findById(id).map(QuizInfoResponse::valueOf)
                .orElseThrow(() -> new ResourceNotFoundException(format("Quiz not found with id: %s", id)));
    }

    public QuizInfoResponse create(Commands.CreateQuizCommand command) {
        var existingLesson = lessonRepository.findById(command.lessonId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));

        var newQuiz = new Quiz(command.name(), command.description(), existingLesson);

        return of(quizRepository.save(newQuiz)).map(QuizInfoResponse::valueOf)
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));
    }

    public QuizInfoResponse update(Long id, Commands.UpdateQuizCommand command) {
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
