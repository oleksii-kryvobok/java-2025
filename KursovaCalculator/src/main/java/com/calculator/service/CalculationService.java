package com.calculator.service;
import com.calculator.entity.Calculations;
//import com.calculator.entity.SavedVariables;
import com.calculator.entity.User;
import com.calculator.logic.ExpressionEvaluator;
import com.calculator.repository.CalculationsRepository;
import com.calculator.repository.SavedVariablesRepository;
import com.calculator.repository.SavedFormulasRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationService {
    private final CalculationsRepository calculationsRepository;
    private final SavedVariablesRepository variableRepository;
    private final SavedFormulasRepository formulaRepository;
    private final ExpressionEvaluator evaluator;

    public String evaluateExpression(String expression, User user)
    {
        Map<String, Double> variables = variableRepository.findByUserId(user.getId())
                .stream().collect(Collectors.toMap(
                        v -> v.getName().toLowerCase(),
                        v -> parseDoubleOrDefault(v.getValue(), 0.0)
                ));
        formulaRepository.findByUserId(user.getId())
                .forEach(f -> {
                    try {
                        String evaluated = evaluator.evaluate(f.getExpression(), variables);
                        variables.put(f.getName().toLowerCase(), Double.parseDouble(evaluated));
                    } catch (Exception e) {
                        variables.put(f.getName().toLowerCase(), 0.0);
                    }
                });
        return evaluator.evaluate(expression, variables);
    }

    public Calculations saveCalculations(User user, String expression, String result)
    {
        Calculations calc = Calculations.builder()
                .user(user)
                .expression(expression)
                .result(result)
                .timestamp(LocalDateTime.now())
                .build();
        return calculationsRepository.save(calc);
    }

    public List<Calculations> getUserHistory(UUID id) { return calculationsRepository.findByUserId(id); }

    private double parseDoubleOrDefault(String value, double defaultVal) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }
}
