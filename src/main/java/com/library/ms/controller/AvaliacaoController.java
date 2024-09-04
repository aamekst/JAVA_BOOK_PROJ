package com.library.ms.controller;

import com.library.ms.domain.dto.Request.AvaliacaoRequestDto;
import com.library.ms.domain.entity.AvaliacaoEntity;
import com.library.ms.domain.entity.LivroEntity;
import com.library.ms.domain.service.AvaliacaoServiceInterface;
import com.library.ms.domain.service.LivroService;
import com.library.ms.domain.service.LivroServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class AvaliacaoController {

    @Autowired
    private LivroServiceInterface livroService;

    @Autowired
    private AvaliacaoServiceInterface avaliacaoService;

    @PostMapping("/avaliacoes")
    public ResponseEntity<String> salvarAvaliacao(@RequestBody AvaliacaoRequestDto dto) {
        // Verifica se o ID do livro está presente no DTO
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().body("ID do livro não fornecido");
        }

        // Obtém o livro a partir do ID fornecido no DTO
        LivroEntity livro = livroService.buscarPorId(dto.getId());
        if (livro == null) {
            return ResponseEntity.badRequest().body("Livro não encontrado");
        }

        // Cria a entidade de avaliação a partir do DTO
        AvaliacaoEntity avaliacao = new AvaliacaoEntity();
        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());
        avaliacao.setLivro(livro);

        // Salva a avaliação
        avaliacaoService.salvarAvaliacao(avaliacao);

        return ResponseEntity.ok("Avaliação salva com sucesso!");
    }
    @GetMapping("/avaliacao")
    public String listarAvaliacoes(Model model) {
        List<AvaliacaoEntity> avaliacoes = avaliacaoService.listarTodasAvaliacoes();
        model.addAttribute("avaliacoes", avaliacoes);
        return "avaliacao"; // Nome do template Thymeleaf
    }





}