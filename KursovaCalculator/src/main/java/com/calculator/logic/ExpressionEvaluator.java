package com.calculator.logic;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class ExpressionEvaluator {

    public String evaluate(String expression, Map<String, Double> variables) {
        try {
            Expression exp = new ExpressionBuilder(expression)
                    .variables(variables.keySet())
                    .build()
                    .setVariables(variables);
            double result = exp.evaluate();
            System.out.println(String.valueOf(result));
            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return "Division by zero";
            }
            return String.valueOf(result);
        } catch (ArithmeticException ae) {
            return "Math error: " + ae.getMessage();
        } catch (Exception e) {
            return "Invalid expression or other error: " + e.getMessage();
        }
    }

    @FunctionalInterface
    interface MathFunction {
        double apply(double value);
    }
}
