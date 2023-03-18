package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.entity.User;
import com.arturfrimu.interview.helper.exception.ExceptionContainer;
import com.arturfrimu.interview.helper.repository.UserRepository;
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
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("User not found with id: %s", id)));
    }

    public User create(Commands.CreateUserCommand command) {
        var newUser = new User(command.name(), command.email());
        return userRepository.save(newUser);
    }

    public User update(Long id, Commands.UpdateUserCommand command) {
        var existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("User not found with id: %s", id)));

        existingUser.setName(command.name());
        existingUser.setEmail(command.email());

        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        var existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("User not found with id: %s", id)));

        userRepository.delete(existingUser);
    }
}
