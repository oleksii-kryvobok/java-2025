package com.calculator.service;
import com.calculator.entity.User;
import com.calculator.entity.SavedFormulas;
import com.calculator.repository.SavedFormulasRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SavedFormulaService {
    private final SavedFormulasRepository repository;
    public SavedFormulas save(User user, String name, String expression)
    {
        var formula = repository.findByUserIdAndName(user.getId(), name)
                .orElse(SavedFormulas.builder().user(user).name(name).build());
        formula.setExpression(expression);
        formula.setLast_updated(LocalDateTime.now());
        return repository.save(formula);
    }
    public List<SavedFormulas> getAll(User user)
    {
        return repository.findByUserId(user.getId());
    }
}
