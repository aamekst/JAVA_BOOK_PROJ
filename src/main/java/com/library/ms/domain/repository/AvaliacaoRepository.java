package com.library.ms.domain.repository;

import com.library.ms.domain.entity.AutorEntity;
import com.library.ms.domain.entity.AvaliacaoEntity;
import com.library.ms.domain.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Integer> {
    //List<AvaliacaoEntity> findByLivro_Nome(String nome);
    List<AvaliacaoEntity> findByLivro_NomeContainingIgnoreCase(String nome);
}