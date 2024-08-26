package com.library.ms.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Avaliacao", schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AvaliacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true)
    private Integer Id;

    @Column(name = "Nota")
    private String Nota;

    @Column(name = "Comentario")
    private String Comentario;

    @ManyToOne
    @JoinColumn(name = "Id_Livros")
    private LivroEntity livro;
}
