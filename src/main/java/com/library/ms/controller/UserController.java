package com.library.ms.controller;

import com.library.ms.domain.dto.Reponse.UserResponseDto;
import com.library.ms.domain.dto.Request.UserRequestDto;
import com.library.ms.domain.entity.UserEntity;
import com.library.ms.domain.repository.UserRepository;
import com.library.ms.domain.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private UserRepository userRepository;


    // Mapeamento da página de cadastro de usuário
    @GetMapping("/usuario/cadastrar")
    public String index(Model model) {
        model.addAttribute("userRequestDto", new UserRequestDto());
        return "cadastrar_usuario";
    }

    @PostMapping("/usuario/cadastrar")
    public String salvarUser(@ModelAttribute UserRequestDto userRequestDto, Model model, RedirectAttributes redirectAttributes) {
        try {
            // Criptografa a senha
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String senhaCriptografada = passwordEncoder.encode(userRequestDto.getSenha());

            // Altera a senha no DTO para a versão criptografada
            userRequestDto.setSenha(senhaCriptografada);

            // Salva o usuário com a senha criptografada
            UserResponseDto userResponseDto = userService.save(userRequestDto);

            // Passa os dados do usuário para a página conta_user.html
            model.addAttribute("usuario", userResponseDto);

            return "conta_user";  // Redireciona diretamente com os dados no modelo
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao cadastrar o usuário: " + e.getMessage());
            return "redirect:/api/usuario/cadastrar";  // Volta para o formulário
        }
    }

    @PostMapping("/usuario/excluir/{id}")
    public String excluirUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            // Chama o serviço para excluir o usuário
            String mensagem = userService.excluirUser(id);

            // Passa a mensagem de sucesso ou erro
            redirectAttributes.addFlashAttribute("mensagem", mensagem);

            // Redireciona de volta para a página de cadastro ou outra página após exclusão
            return "redirect:/api/usuario/cadastrar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir o usuário: " + e.getMessage());
            return "redirect:/api/usuario/cadastrar";  // Redireciona para a página de cadastro em caso de erro
        }
    }

    @GetMapping("/conta_user")
    public String mostrarContaUsuario(Model model) {
        // Aqui você pode adicionar informações sobre o usuário no modelo, se necessário
        return "conta_user";  // Nome do template Thymeleaf
    }


}