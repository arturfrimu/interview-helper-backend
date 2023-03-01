package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.User;
import com.arturfrimu.studies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public boolean deleteUser(long id) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return false;
        }
        userRepository.delete(existingUser);
        return true;
    }
}
