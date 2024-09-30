package com.library.ms.controller;

import com.library.ms.domain.dto.Reponse.LivroResponseDto;
import com.library.ms.domain.dto.Request.AvaliacaoRequestDto;
import com.library.ms.domain.dto.Request.LivroRequestDto;
import com.library.ms.domain.entity.AvaliacaoEntity;
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

    @GetMapping("/consultar")
    public String listarLivros(Model model) {
        List<LivroEntity> livros = livroServiceInterface.listarTodosLivros();
        model.addAttribute("livros", livros);
        return "consultar"; // Nome do template Thymeleaf
    }


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

    @GetMapping("/buscarPorNome")
    public String buscarPorNome(@RequestParam("nome") String nome, Model model) {
        List<LivroEntity> livros = livroServiceInterface.buscarPorNome(nome);
        model.addAttribute("livros", livros);
        return "consultar";  // Nome do template Thymeleaf
    }


    @GetMapping("/livro/editar/{id}")
    public String mostrarFormularioAtualizacao(@PathVariable Integer id, Model model) {
        LivroEntity livro = livroServiceInterface.buscarPorId(id);  // Busca o livro pelo ID
        if (livro == null) {
            return "redirect:/api/consultar"; // Redireciona se o livro não for encontrado
        }

        // Adiciona o livro ao modelo para que os dados possam ser exibidos no formulário
        model.addAttribute("livro", livro);
        model.addAttribute("autores", autorRepository.findAll()); // Adiciona a lista de autores
        model.addAttribute("editoras", editoraRepository.findAll()); // Adiciona a lista de editoras

        return "atualiza_avaliacao";  // Retorna o nome do template Thymeleaf
    }



    @PostMapping("/livro/atualizar/{id}")
    public String atualizar(@PathVariable Integer id, @ModelAttribute LivroRequestDto livroRequestDto) {
        livroRequestDto.setId(id); // Define o ID do DTO a partir do PathVariable
        try {
            livroServiceInterface.atualizar(id, livroRequestDto); // Chama o método de atualização
            return "redirect:/api/consultar";
        } catch (RuntimeException e) {
            return "redirect:/api/consultar?erro=" + e.getMessage(); // Redireciona com mensagem de erro
        }
    }






}

