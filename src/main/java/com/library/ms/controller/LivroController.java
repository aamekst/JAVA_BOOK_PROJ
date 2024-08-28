package com.library.ms.controller;

import com.library.ms.domain.dto.Reponse.LivroResponseDto;
import com.library.ms.domain.dto.Request.AvaliacaoRequestDto;
import com.library.ms.domain.dto.Request.LivroRequestDto;
import com.library.ms.domain.entity.LivroEntity;
import com.library.ms.domain.repository.AutorRepository;
import com.library.ms.domain.repository.EditoraRepository;
import com.library.ms.domain.service.LivroService;
import com.library.ms.domain.service.LivroServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
//@RestController
@Controller
@RequestMapping("/api")
public class LivroController {

    public LivroController(LivroServiceInterface livroServiceInterface, AutorRepository autorRepository, EditoraRepository editoraRepository) {
        this.livroServiceInterface = livroServiceInterface;
        this.autorRepository = autorRepository;
        this.editoraRepository = editoraRepository;
    }

    @GetMapping("/cadastrar")
    public String index() {
        return "index";
    }

    @Autowired
    private LivroService livroService;
    @Autowired
    private final LivroServiceInterface livroServiceInterface;
    @Autowired
    private final AutorRepository autorRepository;
    @Autowired
    private final EditoraRepository editoraRepository;


    @GetMapping("/buscarLivros")
    public ResponseEntity<List<LivroEntity>> buscarLivros(@RequestParam(required = false, defaultValue = "") String nome) {
        if (nome.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        List<LivroEntity> livros = livroService.buscarPorNome(nome);
        System.out.println("Livros encontrados: " + livros); // Adicione este log para depuração
        if (livros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/livros/cadastrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("livroRequestDto", new LivroRequestDto());
        model.addAttribute("autores", autorRepository.findAll());
        model.addAttribute("editoras", editoraRepository.findAll());
        return "livro"; // Nome do template HTML
    }

    @PostMapping("/livros")
    public String salvar(@ModelAttribute LivroRequestDto livroRequestDto, RedirectAttributes redirectAttributes) {
        try {
            LivroResponseDto livroResponseDto = livroServiceInterface.save(livroRequestDto);
            redirectAttributes.addFlashAttribute("mensagem", "Livro cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace no console
            redirectAttributes.addFlashAttribute("erro", "Erro ao cadastrar o livro: " + e.getMessage());
        }
        return "redirect:/api/livros/cadastrar"; // Redireciona para o formulário de cadastro
    }
    //cadastrar livro
    //@PostMapping("/livros")
    //public LivroResponseDto salvar(@RequestBody LivroRequestDto livroRequestDto) {
     //   return livroServiceInterface.save(livroRequestDto);
    //}

}

