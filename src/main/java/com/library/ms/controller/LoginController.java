package com.library.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Exibe a página de login
    @GetMapping("/login")
    public String login() {
        return "login_usuario";  // Nome do seu template de login
    }
}
