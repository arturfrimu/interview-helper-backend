package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.User;
import com.arturfrimu.studies.service.UserService;
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
    public ResponseEntity<User> create(@RequestBody User user) {
        var createdUser = userService.create(user);
        return ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        var updatedUser = userService.update(id, user);
        return ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ok().build();
    }
}
