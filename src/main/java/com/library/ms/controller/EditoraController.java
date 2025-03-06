package com.library.ms.controller;

import com.library.ms.domain.dto.Request.EditoraRequestDto;
import com.library.ms.domain.entity.EditoraEntity;
import com.library.ms.domain.service.EditoraServiceInterface;
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
public class EditoraController {

    @Autowired
    private EditoraServiceInterface editoraService;  // Apenas esta instância é necessária

    // Mapeamento da página de cadastro de usuário
    @GetMapping("/editora/cadastrar")
    public String index(Model model) {
        model.addAttribute("editoraRequestDto", new EditoraRequestDto());
        return "cadastrar_editora";
    }

    @PostMapping("/editora/cadastrar")
    public String salvarEditora(@ModelAttribute EditoraRequestDto editoraRequestDto, RedirectAttributes redirectAttributes) {
        try {
            // Chama o serviço para salvar o autor
            editoraService.save(editoraRequestDto);

            // Mensagem de sucesso
            redirectAttributes.addFlashAttribute("mensagem", "Editora cadastrado com sucesso!");
        } catch (Exception e) {
            // Mensagem de erro
            redirectAttributes.addFlashAttribute("erro", "Erro ao cadastrar: " + e.getMessage());
        }

        // Redireciona para a página de cadastro correta
        return "redirect:/api/consultar/editora";
    }

    @GetMapping("/consultar/editora")
    public String listarEditora(Model model) {
        try {
            List<EditoraEntity> editoras = editoraService.listarTodosEditoras();  // Certifique-se de que o serviço está retornando dados corretamente
            model.addAttribute("editoras", editoras);  // Passando a lista para o modelo
            return "consultar_editoras"; // Nome do template Thymeleaf
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao listar: " + e.getMessage());
            return "home"; // Retorna a mesma página com a mensagem de erro
        }
    }

}
