package com.calculator.dto;
import lombok.*;

@Data
@Builder
public class CalculationResponseDTO {
    private String expression;
    private String result;
    private String timestamp;
}
