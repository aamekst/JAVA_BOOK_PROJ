package com.library.ms.domain.service;

import com.library.ms.domain.entity.AvaliacaoEntity;
import com.library.ms.domain.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoServiceInterface {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Override
    public void salvarAvaliacao(AvaliacaoEntity avaliacao) {
        avaliacaoRepository.save(avaliacao);
    }

    @Override
    public List<AvaliacaoEntity> buscarAvaliacoesPorLivroId(Integer Id) {
        return List.of();
    }

    @Override
    public List<AvaliacaoEntity> listarTodasAvaliacoes() {
        return avaliacaoRepository.findAll(); // Método para listar todas as avaliações
    }

    @Override
    public List<AvaliacaoEntity> buscarPorNomeDeLivro(String nome) {
        return List.of();
    }

    public List<AvaliacaoEntity> buscarAvaliacoesPorLivro(String nomeLivro) {
        return avaliacaoRepository.findByLivro_NomeContainingIgnoreCase(nomeLivro);  // Busca usando LIKE no nome
    }
}