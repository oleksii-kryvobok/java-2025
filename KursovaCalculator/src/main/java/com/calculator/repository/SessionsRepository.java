package com.calculator.repository;
import com.calculator.entity.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
public interface SessionsRepository extends JpaRepository<Sessions, UUID>
{
    Optional<Sessions> findByToken(String token);
}