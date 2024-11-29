package com.library.ms.domain.dto.Reponse;

public class UserResponseDto {
        private Integer id;
        private String Nome;
        private String email;
        private String senha;

        public Integer getId() {
            return this.id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNome() {
            return this.Nome;
        }

        public void setNome(String nome) {
            this.Nome = nome;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return this.senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

}
