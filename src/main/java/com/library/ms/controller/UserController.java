package com.library.ms.controller;

import com.library.ms.domain.dto.Reponse.UserResponseDto;
import com.library.ms.domain.dto.Request.UserRequestDto;
import com.library.ms.domain.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    // Mapeamento da página de cadastro de usuário
    @GetMapping("/usuario/cadastrar")
    public String index(Model model) {
        model.addAttribute("userRequestDto", new UserRequestDto());
        return "cadastrar_usuario";
    }

    // Salvando o usuário via POST
    @PostMapping("/usuario/cadastrar") // URL alterada para /usuario/cadastrar
    public String salvarUser(@ModelAttribute UserRequestDto userRequestDto, RedirectAttributes redirectAttributes) {
        try {
            // Chama o serviço para salvar o usuário
            UserResponseDto userResponseDto = userService.save(userRequestDto);
            // Mensagem de sucesso
            redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            // Mensagem de erro
            redirectAttributes.addFlashAttribute("erro", "Erro ao cadastrar o usuário: " + e.getMessage());
        }
        // Redireciona para a página de cadastro após salvar
        return "redirect:/api/usuario/cadastrar"; // A URL corrigida aqui também
    }
}
