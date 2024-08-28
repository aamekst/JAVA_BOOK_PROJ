package com.library.ms.domain.dto.Request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera automaticamente métodos getters, setters, toString, equals e hashCode.
@NoArgsConstructor
public class AvaliacaoRequestDto {
    private Integer id;
    private String nome;
    private String nota;
    private String comentario;
    private Integer idlivro; // ID do Autor

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getIdlivro() {
        return idlivro;
    }

    public void setIdlivro(Integer idlivro) {
        this.idlivro = idlivro;
    }
}

