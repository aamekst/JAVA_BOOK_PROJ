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
    private Integer id;

    @ManyToOne
    private LivroEntity livro;

    private String nota;
    private String comentario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LivroEntity getLivro() {
        return livro;
    }

    public void setLivro(LivroEntity livro) {
        this.livro = livro;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
