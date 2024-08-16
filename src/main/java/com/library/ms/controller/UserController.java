package com.library.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {

    @GetMapping("/teste")
    public String teste() {
        return "teste";
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }
}
