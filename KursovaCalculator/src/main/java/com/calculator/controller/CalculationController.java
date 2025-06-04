package com.calculator.controller;
import com.calculator.entity.Calculations;
import com.calculator.entity.User;
import com.calculator.service.CalculationService;
import com.calculator.service.UserService;
import lombok.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;
import com.calculator.dto.CalculationRequestDTO;
import com.calculator.dto.CalculationResponseDTO;

@RestController
@RequestMapping("/api/calculations")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class CalculationController {
    private final CalculationService calculationService;
    private final UserService userService;

    @PostMapping("/evaluate")
    public CalculationResponseDTO evaluate(@RequestBody CalculationRequestDTO dto, @AuthenticationPrincipal Jwt jwt)
    {
        String email = jwt.getClaimAsString("email");
        User user = userService.getUserByEmail(email);
        String result = calculationService.evaluateExpression(dto.getExpression(), user);
        calculationService.saveCalculations(user, dto.getExpression(), result);
        return CalculationResponseDTO.builder()
                .expression(dto.getExpression())
                .result(result)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    @GetMapping("/history")
    public List<CalculationResponseDTO> getHistory(@AuthenticationPrincipal Jwt jwt)
    {
        String email = jwt.getClaimAsString("email");
        User user = userService.getUserByEmail(email);
        return calculationService.getUserHistory(user.getId()).stream()
                .map(calc -> CalculationResponseDTO.builder()
                        .expression(calc.getExpression())
                        .result(calc.getResult())
                        .timestamp(calc.getTimestamp().toString())
                        .build())
                .toList();
    }

}
