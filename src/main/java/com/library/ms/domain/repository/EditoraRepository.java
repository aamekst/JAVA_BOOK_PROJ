package com.library.ms.domain.repository;

import com.library.ms.domain.entity.EditoraEntity;
import com.library.ms.domain.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditoraRepository extends JpaRepository<EditoraEntity, Integer> {
    // Você pode adicionar métodos personalizados aqui se precisar
}