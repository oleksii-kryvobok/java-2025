package com.calculator.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "saved_formulas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedFormulas {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private String name;
    private String expression;
    private LocalDateTime last_updated;
}
