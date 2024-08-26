package com.library.ms.domain.dto.Request;

import com.library.ms.domain.entity.AutorEntity;
import com.library.ms.domain.entity.EditoraEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera automaticamente métodos getters, setters, toString, equals e hashCode.
@NoArgsConstructor
public class LivroRequestDto {
    private Integer id;
    private String Nome;
    private String data_publicacao;
    private String descricao;
    private String numero_paginas;
    private Integer idAutor; // ID do Autor
    private Integer idEditora; // ID da Editora
    private String urlFoto;

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

    public String getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(String data_publicacao) {
        this.data_publicacao = data_publicacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumero_paginas() {
        return numero_paginas;
    }

    public void setNumero_paginas(String numero_paginas) {
        this.numero_paginas = numero_paginas;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public Integer getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(Integer idEditora) {
        this.idEditora = idEditora;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}

