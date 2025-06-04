package com.calculator.controller;
import com.calculator.dto.UserInfoDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginSuccessController {
    @GetMapping("/login-success")
    public String afterLogin() {
        return "redirect:/api/auth/me";
    }
}
