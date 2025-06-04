package com.calculator.logic;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class ExpressionEvaluatorTest {
    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    @Test
    void testBasicEvaluation() {
        String result = evaluator.evaluate("2 + 3 * 4", Map.of());
        assertThat(result).isEqualTo("14.0");
    }

    @Test
    void testWithVariables() {
        String result = evaluator.evaluate("a * b", Map.of("a", 2.0, "b", 5.0));
        assertThat(result).isEqualTo("10.0");
    }

    @Test
    void testDivisionByZero() {
        String result = evaluator.evaluate("10 / 0", Map.of());
        assertThat(result).isEqualTo("Math error: Division by zero!");
    }
}
