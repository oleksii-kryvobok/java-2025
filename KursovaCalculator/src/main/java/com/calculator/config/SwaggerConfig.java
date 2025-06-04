package com.calculator.config;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Keycloak",
        type = SecuritySchemeType.OAUTH2,
        in = SecuritySchemeIn.HEADER,
        bearerFormat = "JWT",
        scheme = "bearer",
        flows = @io.swagger.v3.oas.annotations.security.OAuthFlows(
                authorizationCode = @io.swagger.v3.oas.annotations.security.OAuthFlow(
                        authorizationUrl = "http://localhost:8080/realms/smartcalc/protocol/openid-connect/auth",
                        tokenUrl = "http://localhost:8080/realms/smartcalc/protocol/openid-connect/token"
                )
        )

)
public class SwaggerConfig {
}
