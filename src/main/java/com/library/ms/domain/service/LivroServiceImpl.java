package com.library.ms.domain.service;

import com.library.ms.domain.dto.Reponse.LivroResponseDto;
import com.library.ms.domain.dto.Request.LivroRequestDto;
import com.library.ms.domain.entity.AutorEntity;
import com.library.ms.domain.entity.EditoraEntity;
import com.library.ms.domain.entity.LivroEntity;
import com.library.ms.domain.repository.AutorRepository;
import com.library.ms.domain.repository.EditoraRepository;
import com.library.ms.domain.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImpl implements LivroServiceInterface {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final EditoraRepository editoraRepository;

    @Autowired
    public LivroServiceImpl(LivroRepository livroRepository, AutorRepository autorRepository, EditoraRepository editoraRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.editoraRepository = editoraRepository;
    }

    @Override
    public LivroResponseDto save(LivroRequestDto livroRequestDto) {
        // Verifica se já existe um livro com o mesmo nome e autor
        boolean livroExistente = livroRepository.existsByNomeAndAutorId(livroRequestDto.getNome(), livroRequestDto.getIdAutor());
        if (livroExistente) {
            throw new IllegalArgumentException("Já existe um livro com o mesmo nome e autor.");
        }

        // Criando a entidade LivroEntity
        LivroEntity livroEntity = new LivroEntity();

        // Transferindo os dados do DTO para a entidade LivroEntity
        livroEntity.setNome(livroRequestDto.getNome());
        livroEntity.setData_publicacao(livroRequestDto.getData_publicacao());
        livroEntity.setDescricao(livroRequestDto.getDescricao());
        livroEntity.setNumero_paginas(livroRequestDto.getNumero_paginas());
        livroEntity.setUrl_foto(livroRequestDto.getUrlFoto());

        // Buscando o Autor e a Editora pelo ID e associando ao Livro
        AutorEntity autor = autorRepository.findById(livroRequestDto.getIdAutor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        EditoraEntity editora = editoraRepository.findById(livroRequestDto.getIdEditora())
                .orElseThrow(() -> new RuntimeException("Editora não encontrada"));

        livroEntity.setAutor(autor);
        livroEntity.setEditora(editora);

        // Salvando o livro no banco de dados
        LivroEntity savedLivro = (LivroEntity) livroRepository.save(livroEntity);

        // Criando o DTO de resposta
        LivroResponseDto livroResponseDto = new LivroResponseDto();
        livroResponseDto.setId(savedLivro.getId());
        livroResponseDto.setNome(savedLivro.getNome());
        livroResponseDto.setData_publicacao(savedLivro.getData_publicacao());
        livroResponseDto.setDescricao(savedLivro.getDescricao());
        livroResponseDto.setNumero_paginas(savedLivro.getNumero_paginas());
        livroResponseDto.setUrlFoto(savedLivro.getUrl_foto());
        livroResponseDto.setNomeAutor(savedLivro.getAutor().getNome());
        livroResponseDto.setNomeEditora(savedLivro.getEditora().getNome());

        // Retornando o DTO de resposta
        return livroResponseDto;
    }


    @Override
    public LivroEntity buscarPorId(Integer id) {
        return (LivroEntity) livroRepository.findById(id).orElse(null);
    }

    @Override
    public List<LivroEntity> listarTodosLivros() {
        return livroRepository.findAll();
    }

    @Override
    public List<LivroEntity> buscarPorNome(String nome) {
        return livroRepository.findByNomeContainingIgnoreCase(nome);
    }


    @Override
    public LivroResponseDto atualizar(Integer id, LivroRequestDto livroRequestDto) {
        // Verifica se o ID é nulo
        if (id == null) {
            throw new IllegalArgumentException("O ID do livro não pode ser nulo.");
        }

        // Busca o livro pelo ID
        Optional<LivroEntity> optionalLivro = livroRepository.findById(id);

        // Verifica se o livro existe
        if (!optionalLivro.isPresent()) {
            throw new RuntimeException("Livro não encontrado com o ID: " + id);
        }

        LivroEntity exist = optionalLivro.get(); // Pega o LivroEntity

        // Atualiza os dados do livro
        exist.setNome(livroRequestDto.getNome());
        exist.setData_publicacao(livroRequestDto.getData_publicacao());
        exist.setDescricao(livroRequestDto.getDescricao());
        exist.setNumero_paginas(livroRequestDto.getNumero_paginas());
        exist.setUrl_foto(livroRequestDto.getUrlFoto());

        // Atualiza autor
        AutorEntity autor = autorRepository.findById(livroRequestDto.getIdAutor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com o ID: " + livroRequestDto.getIdAutor()));
        exist.setAutor(autor);

        // Atualiza editora
        EditoraEntity editora = editoraRepository.findById(livroRequestDto.getIdEditora())
                .orElseThrow(() -> new RuntimeException("Editora não encontrada com o ID: " + livroRequestDto.getIdEditora()));
        exist.setEditora(editora);

        // Salva o livro atualizado
        LivroEntity savedLivro = (LivroEntity) livroRepository.save(exist);

        // Cria e retorna o DTO de resposta
        LivroResponseDto livroResponseDto = new LivroResponseDto();
        livroResponseDto.setId(savedLivro.getId());
        livroResponseDto.setNome(savedLivro.getNome());
        livroResponseDto.setDescricao(savedLivro.getDescricao());
        livroResponseDto.setNumero_paginas(savedLivro.getNumero_paginas());
        livroResponseDto.setNomeAutor(savedLivro.getAutor().getNome());
        livroResponseDto.setNomeEditora(savedLivro.getEditora().getNome());

        return livroResponseDto;
    }





}
