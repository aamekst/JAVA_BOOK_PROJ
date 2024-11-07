package com.library.ms.domain.service;

import com.library.ms.domain.dto.Reponse.LivroResponseDto;
import com.library.ms.domain.dto.Request.LivroRequestDto;
import com.library.ms.domain.entity.AvaliacaoEntity;
import com.library.ms.domain.entity.LivroEntity;

import java.util.List;

public interface AvaliacaoServiceInterface {

    void salvarAvaliacao(AvaliacaoEntity avaliacao);


    List<AvaliacaoEntity> buscarAvaliacoesPorLivroId(Integer Id);

    List<AvaliacaoEntity> listarTodasAvaliacoes();

    List<AvaliacaoEntity> buscarPorNomeDeLivro(String nome);

    List<AvaliacaoEntity> buscarAvaliacoesPorLivro(String nome);

    //List<AvaliacaoEntity> buscarPorNome(String nome);


}
