package com.library.ms.domain.service;

import com.library.ms.domain.entity.LivroEntity;
import com.library.ms.domain.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroEntity> buscarPorNome(String nome) {
        return livroRepository.findByNomeContainingIgnoreCase(nome);
    }
}
