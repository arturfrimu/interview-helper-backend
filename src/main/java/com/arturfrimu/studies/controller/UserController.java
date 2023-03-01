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
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public User find(@PathVariable Long id) {
        return userService.find(id);
    }

    @PostMapping("")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ok().build();
    }
}
