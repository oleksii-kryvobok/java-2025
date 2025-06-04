package com.calculator.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String email;
    private String passwordHash;
    private LocalDateTime createdAt;
}
