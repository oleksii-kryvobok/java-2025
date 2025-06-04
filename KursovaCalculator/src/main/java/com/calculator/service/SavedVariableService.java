package com.calculator.service;
import com.calculator.entity.User;
import com.calculator.entity.SavedVariables;
import com.calculator.repository.SavedVariablesRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SavedVariableService {
    private final SavedVariablesRepository repository;
    public SavedVariables save(User user, String name, String value)
    {
        var variable = repository.findByUserIdAndName(user.getId(), name)
                .orElse(SavedVariables.builder().user(user).name(name).build());
        variable.setValue(value);
        variable.setLast_updated(LocalDateTime.now());
        return repository.save(variable);
    }
    public List<SavedVariables> getAll(User user)
    {
        return repository.findByUserId(user.getId());
    }
}
