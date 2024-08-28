package com.library.ms.domain.dto.Reponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AvaliacaoResponseDto {
    private Integer id;
    private String comentario;
    private String nota;
    private String nomelivro;

    public String getNomelivro() {
        return nomelivro;
    }

    public void setNomelivro(String nomelivro) {
        this.nomelivro = nomelivro;
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
