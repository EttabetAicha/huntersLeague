package org.aicha.hunters_leagues.service;

import org.aicha.hunters_leagues.domain.User;
import org.aicha.hunters_leagues.domain.enums.Role;
import org.aicha.hunters_leagues.repository.UserRepository;
import org.aicha.hunters_leagues.web.exception.ResourceNotFoundException;
import org.aicha.hunters_leagues.web.vm.RegisterVM;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(RegisterVM registerVM) {
        User user = User.builder()
                .username(registerVM.getUsername())
                .password(registerVM.getPassword())
                .firstName(registerVM.getFirstName())
                .lastName(registerVM.getLastName())
                .cin(registerVM.getCin())
                .email(registerVM.getEmail())
                .nationality(registerVM.getNationality())
                .role(registerVM.getRole())
                .joinDate(LocalDateTime.now())
                .build();
        validateUser(user);
        checkUserExistence(user);
        return userRepository.save(user);
    }

    public boolean login(User userLogin) {
        User user = userRepository.findByUsername(userLogin.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        if (userLogin.getPassword().equals(user.getPassword())) {
            return true;
        } else {
            throw new ResourceNotFoundException("Invalid credentials.");
        }
    }

    private void validateUser(User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null || user.getEmail() == null || user.getPassword().length() < 8) {
            throw new UsernameNotFoundException("Invalid user");
        }
    }

    private void checkUserExistence(User user) {
        if (userRepository.existsByEmail(user.getEmail()) || userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameNotFoundException("User already exists");
        }
    }
}