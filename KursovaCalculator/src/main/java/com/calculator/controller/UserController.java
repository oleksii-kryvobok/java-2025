package com.calculator.controller;
import com.calculator.entity.User;
import com.calculator.service.UserService;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import com.calculator.dto.UserResponseDTO;
import com.calculator.dto.UserRegistrationDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRegistrationDTO dto)
    {
        User user = userService.registerUser(dto.getUsername(), dto.getEmail(), dto.getPassword());
        return UserResponseDTO.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
