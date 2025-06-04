package com.calculator.dto;
import lombok.*;

@Data
@Builder
public class UserResponseDTO {
    private String id;
    private String username;
    private String email;
}
