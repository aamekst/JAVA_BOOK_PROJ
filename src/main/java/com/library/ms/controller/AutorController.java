package com.library.ms.controller;

import com.library.ms.domain.dto.Reponse.AutorResponseDto;
import com.library.ms.domain.dto.Reponse.UserResponseDto;
import com.library.ms.domain.dto.Request.AutorRequestDto;
import com.library.ms.domain.dto.Request.UserRequestDto;
import com.library.ms.domain.entity.AutorEntity;
import com.library.ms.domain.entity.LivroEntity;
import com.library.ms.domain.service.AutorServiceInterface;
import com.library.ms.domain.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api")
public class AutorController {

    @Autowired
    private AutorServiceInterface autorService;  // Apenas esta instância é necessária

    // Mapeamento da página de cadastro de usuário
    @GetMapping("/autor/cadastrar")
    public String index(Model model) {
        model.addAttribute("autorRequestDto", new AutorRequestDto());
        return "cadastrar_autor";
    }

    @PostMapping("/autor/cadastrar")
    public String salvarAutor(@ModelAttribute AutorRequestDto autorRequestDto, RedirectAttributes redirectAttributes) {
        try {
            // Chama o serviço para salvar o autor
            autorService.save(autorRequestDto);

            // Mensagem de sucesso
            redirectAttributes.addFlashAttribute("mensagem", "Autor cadastrado com sucesso!");
        } catch (Exception e) {
            // Mensagem de erro
            redirectAttributes.addFlashAttribute("erro", "Erro ao cadastrar o autor: " + e.getMessage());
        }

        // Redireciona para a página de cadastro correta
        return "redirect:/api/consultar/autor";
    }

    @GetMapping("/consultar/autor")
    public String listarAutor(Model model) {
        try {
            List<AutorEntity> autores = autorService.listarTodosAutores();  // Certifique-se de que o serviço está retornando dados corretamente
            model.addAttribute("autores", autores);  // Passando a lista para o modelo
            return "consultar_autores"; // Nome do template Thymeleaf
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao listar autores: " + e.getMessage());
            return "home"; // Retorna a mesma página com a mensagem de erro
        }
    }

}
