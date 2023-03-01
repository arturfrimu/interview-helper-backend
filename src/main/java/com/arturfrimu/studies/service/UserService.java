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

    public User find(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public void delete(long id) {
        var existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("User not found with id: %s", id)));
        userRepository.delete(existingUser);
    }
}
