package com.calculator.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "calculations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calculations {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String expression;

    @Column(columnDefinition = "TEXT")
    private String result;
    private LocalDateTime timestamp;
}
