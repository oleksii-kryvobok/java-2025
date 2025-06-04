package com.calculator.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "saved_variables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedVariables {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User user;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String value;
    private LocalDateTime last_updated;
}
