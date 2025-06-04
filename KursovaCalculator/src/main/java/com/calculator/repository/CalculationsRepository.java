package com.calculator.repository;
import com.calculator.entity.Calculations;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CalculationsRepository extends JpaRepository<Calculations, UUID>
{
    List<Calculations> findByUserId(UUID id);
}