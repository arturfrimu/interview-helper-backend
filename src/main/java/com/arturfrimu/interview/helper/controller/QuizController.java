package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.dto.request.Requests;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateQuizRequest;
import com.arturfrimu.interview.helper.dto.response.Response.QuizInfoResponse;
import com.arturfrimu.interview.helper.service.QuizService;
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

import static java.util.Optional.of;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<List<QuizInfoResponse>> list() {
        var quizzes = quizService.list();
        return ok().body(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizInfoResponse> find(@PathVariable Long id) {
        var quiz = quizService.find(id);
        return ok().body(quiz);
    }

    @PostMapping
    public ResponseEntity<QuizInfoResponse> create(@RequestBody CreateQuizRequest body) {
        var command = of(body).map(Commands.CreateQuizCommand::valueOf).get();
        var createdQuiz = quizService.create(command);
        return ok().body(createdQuiz);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizInfoResponse> update(@PathVariable Long id, @RequestBody Requests.UpdateQuizRequest body) {
        var command = of(body).map(Commands.UpdateQuizCommand::valueOf).get();
        var updatedQuiz = quizService.update(id, command);
        return ok().body(updatedQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        quizService.delete(id);
        return ok().build();
    }
}
