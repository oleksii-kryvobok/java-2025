package com.calculator.controller;
import com.calculator.entity.SavedFormulas;
import com.calculator.entity.User;
import com.calculator.service.SavedFormulaService;
import com.calculator.service.UserService;
import com.calculator.dto.FormulaDTO;
import lombok.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/formulas")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class SavedFormulaController {
    private final SavedFormulaService service;
    private final UserService userService;

    @PostMapping
    public SavedFormulas save(@RequestBody FormulaDTO dto, @AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getClaimAsString("email");
        User user = userService.getUserByEmail(email);
        return service.save(user, dto.getName(), dto.getExpression());
    }

    @GetMapping
    public List<SavedFormulas> getAll(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getClaimAsString("email");
        User user = userService.getUserByEmail(email);
        return service.getAll(user);
    }
}