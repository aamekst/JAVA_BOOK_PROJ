package com.library.ms.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

@Entity
@Table(name = "Livros", schema = "dbo", uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "id_autor"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true)
    private Integer Id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Descricao")
    private String Descricao;

    @Column(name = "Data_publicacao")
    private String Data_publicacao;

    @Column(name = "Numero_paginas")
    private String numero_paginas;

    @Column(name = "Url_foto")
    private String Url_foto;

    @ManyToOne
    @JoinColumn(name = "Id_Editora")
    private EditoraEntity editora;

    @ManyToOne
    @JoinColumn(name = "Id_Autor")
    private AutorEntity autor;


   // @ManyToOne
   // @JoinColumn(name = "Id_user") // nome da coluna na tabela Livro que referencia Usuario
  //  private User user;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getData_publicacao() {
        return Data_publicacao;
    }

    public void setData_publicacao(String data_publicacao) {
        Data_publicacao = data_publicacao;
    }

    public String getNumero_paginas() {
        return numero_paginas;
    }

    public void setNumero_paginas(String numero_paginas) {
        this.numero_paginas = numero_paginas;
    }

    public String getUrl_foto() {
        return Url_foto;
    }

    public void setUrl_foto(String url_foto) {
        Url_foto = url_foto;
    }

    public EditoraEntity getEditora() {
        return editora;
    }

    public void setEditora(EditoraEntity editora) {
        this.editora = editora;
    }

    public AutorEntity getAutor() {
        return autor;
    }

    public void setAutor(AutorEntity autor) {
        this.autor = autor;
    }
}
