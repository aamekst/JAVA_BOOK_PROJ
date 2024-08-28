package com.library.ms.domain.repository;

import com.library.ms.domain.entity.AutorEntity;
import com.library.ms.domain.entity.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Integer> {
    // Você pode adicionar métodos personalizados aqui se precisar
}