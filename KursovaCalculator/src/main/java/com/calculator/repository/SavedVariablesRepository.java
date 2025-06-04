package com.calculator.repository;
import com.calculator.entity.SavedVariables;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface SavedVariablesRepository extends JpaRepository<SavedVariables, UUID>
{
    List<SavedVariables> findByUserId(UUID id);
    Optional<SavedVariables> findByUserIdAndName(UUID id, String name);
}
