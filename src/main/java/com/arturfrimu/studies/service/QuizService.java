package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Quiz;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;

    public List<Quiz> list() {
        return quizRepository.findAll();
    }

    public Quiz find(Long id) {
        return quizRepository.findById(id).orElse(null);
    }

    public Quiz create(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Quiz update(Long id, Quiz quiz) {
        Quiz existingQuiz = quizRepository.findById(id).orElse(null);
        if (existingQuiz == null) {
            return null;
        }
        existingQuiz.setName(quiz.getName());
        existingQuiz.setDescription(quiz.getDescription());
        return quizRepository.save(existingQuiz);
    }

    public void delete(Long id) {
        var existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Quiz not found with id: %s", id)));
        quizRepository.delete(existingQuiz);
    }

}
