package com.calculator.service;
import com.calculator.entity.User;
import com.calculator.logic.ExpressionEvaluator;
import com.calculator.repository.SavedFormulasRepository;
import com.calculator.repository.SavedVariablesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Map;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class CalculationServiceTest {
    @Test
    void testEvaluateExpressionWithNoVars() {
        var evaluator = new ExpressionEvaluator();
        var variablesRepo = Mockito.mock(SavedVariablesRepository.class);
        var formulasRepo = Mockito.mock(SavedFormulasRepository.class);
        var calcRepo = Mockito.mock(com.calculator.repository.CalculationsRepository.class);
        CalculationService service = new CalculationService(calcRepo, variablesRepo, formulasRepo, evaluator);
        User user = User.builder()
                .id(UUID.randomUUID())
                .username("test")
                .email("test@test.com")
                .build();
        Mockito.when(variablesRepo.findByUserId(user.getId())).thenReturn(java.util.List.of());
        Mockito.when(formulasRepo.findByUserId(user.getId())).thenReturn(java.util.List.of());
        String result = service.evaluateExpression("6 / 3 + 2", user);
        assertThat(result).isEqualTo("4.0");
    }
}