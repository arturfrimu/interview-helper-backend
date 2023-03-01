package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.User;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public User find(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("User not found with id: %s", id)));
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User command) {
        var existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("User not found with id: %s", id)));

        existingUser.setName(command.getName());
        existingUser.setEmail(command.getEmail());

        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        var existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("User not found with id: %s", id)));
        userRepository.delete(existingUser);
    }
}
