package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateUserCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateUserCommand;
import com.arturfrimu.studies.entity.User;
import com.arturfrimu.studies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.arturfrimu.studies.exception.ExceptionContainer.ResourceNotFoundException;
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

    public User create(CreateUserCommand command) {
        var newUser = new User(command.name(), command.email());
        return userRepository.save(newUser);
    }

    public User update(Long id, UpdateUserCommand command) {
        var existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("User not found with id: %s", id)));

        existingUser.setName(command.name());
        existingUser.setEmail(command.email());

        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        var existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("User not found with id: %s", id)));

        userRepository.delete(existingUser);
    }
}
