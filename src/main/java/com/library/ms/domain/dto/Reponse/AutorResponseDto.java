package com.library.ms.domain.dto.Reponse;

public class AutorResponseDto {
        private Integer id;
        private String Nome;

    public AutorResponseDto(Integer id, String nome) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
