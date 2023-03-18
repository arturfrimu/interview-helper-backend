package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.dto.request.Requests;
import com.arturfrimu.interview.helper.entity.User;
import com.arturfrimu.interview.helper.service.UserService;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> list() {
        var users = userService.list();
        return ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> find(@PathVariable Long id) {
        var user = userService.find(id);
        return ok(user);
    }

    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody Requests.CreateUserRequest body) {
        var command = of(body).map(Commands.CreateUserCommand::valueOf).get();
        var createdUser = userService.create(command);
        return ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody Requests.UpdateUserRequest body) {
        var command = of(body).map(Commands.UpdateUserCommand::valueOf).get();
        var updatedUser = userService.update(id, command);
        return ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ok().build();
    }
}
