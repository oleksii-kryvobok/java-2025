package com.calculator.repository;
import com.calculator.entity.SavedFormulas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface SavedFormulasRepository extends JpaRepository<SavedFormulas, UUID>
{
    List<SavedFormulas> findByUserId(UUID id);
    Optional<SavedFormulas> findByUserIdAndName(UUID id, String name);
}
