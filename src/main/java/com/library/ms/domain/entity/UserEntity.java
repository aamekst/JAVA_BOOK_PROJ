package com.library.ms.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Usuario", schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true)
    private Integer Id;

    @Column(name = "username", unique = true)
    private String Nome;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "senha")
    private String Senha;

    //@OneToMany(mappedBy = "usuario")
    //private Set<LivroEntity> livros;
}


