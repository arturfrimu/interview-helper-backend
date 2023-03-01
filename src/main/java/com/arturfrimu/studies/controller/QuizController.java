package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Quiz;
import com.arturfrimu.studies.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public List<Quiz> list() {
        return quizService.list();
    }

    @GetMapping("/{id}")
    public Quiz find(@PathVariable Long id) {
        return quizService.find(id);
    }

    @PostMapping
    public Quiz create(@RequestBody Quiz quiz) {
        return quizService.create(quiz);
    }

    @PutMapping("/{id}")
    public Quiz update(@PathVariable Long id, @RequestBody Quiz quiz) {
        return quizService.update(id, quiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        quizService.delete(id);
        return ok().build();
    }
}
