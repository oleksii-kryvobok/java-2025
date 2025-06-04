package com.calculator.controller;
import com.calculator.dto.UserInfoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

@SecurityRequirement(name = "Keycloak")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final OAuth2AuthorizedClientService clientService;

    public AuthController(OAuth2AuthorizedClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/access-token")
    public String getAccessToken(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                authentication.getName()
        );

        return client.getAccessToken().getTokenValue();
    }

    @GetMapping("/me")
    public UserInfoDTO getCurrentUser(@AuthenticationPrincipal OidcUser oidcUser)
    {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setUsername(oidcUser.getPreferredUsername());
        dto.setEmail(oidcUser.getEmail());
        return dto;
    }
}