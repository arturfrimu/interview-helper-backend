package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Quiz;
import com.arturfrimu.studies.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElse(null);
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(Long id, Quiz quiz) {
        Quiz existingQuiz = quizRepository.findById(id).orElse(null);
        if (existingQuiz == null) {
            return null;
        }
        existingQuiz.setName(quiz.getName());
        existingQuiz.setDescription(quiz.getDescription());
        return quizRepository.save(existingQuiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

}
