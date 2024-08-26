package com.library.ms.domain.repository;

import com.library.ms.domain.entity.LivroEntity;
import com.library.ms.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository<Livro> extends JpaRepository<LivroEntity, Integer> {

    @Query("SELECT l FROM LivroEntity l WHERE LOWER(l.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<LivroEntity> findByNomeContainingIgnoreCase(String nome);

    Optional<LivroEntity> findByNome(String nome);

    boolean existsByNomeAndAutorId(String nome, Integer idAutor);
}