package com.calculator.controller;
import com.calculator.entity.SavedVariables;
import com.calculator.entity.User;
import com.calculator.service.SavedVariableService;
import com.calculator.service.UserService;
import com.calculator.dto.VariableDTO;
import lombok.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/variables")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class SavedVariableController {
    private final SavedVariableService service;
    private final UserService userService;

    @PostMapping
    public SavedVariables save(@RequestBody VariableDTO dto, @AuthenticationPrincipal Jwt jwt)
    {
        String email = jwt.getClaimAsString("email");
        User user = userService.getUserByEmail(email);
        return service.save(user, dto.getName(), dto.getValue());
    }

    @GetMapping
    public List<SavedVariables> getAll(@AuthenticationPrincipal Jwt jwt)
    {
        String email = jwt.getClaimAsString("email");
        User user = userService.getUserByEmail(email);
        return service.getAll(user);
    }
}
