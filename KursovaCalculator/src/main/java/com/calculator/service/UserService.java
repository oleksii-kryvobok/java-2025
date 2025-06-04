package com.calculator.service;
import com.calculator.entity.User;
import com.calculator.repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;
//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(String username, String email, String passwordHash)
    {
        if (userRepository.existsByEmail(email)) throw new IllegalArgumentException("Email already in use");
        User user = User.builder()
                .username(username)
                .email(email)
                .passwordHash(passwordHash)
                .createdAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    public User getUserByUID(UUID id)
    {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User getUserByEmail(String email)
    {
        return userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = User.builder()
                    .email(email)
                    .username(email.split("@")[0])
                    .passwordHash("")
                    .createdAt(LocalDateTime.now())
                    .build();
            return userRepository.save(newUser);
        });
    }
}
