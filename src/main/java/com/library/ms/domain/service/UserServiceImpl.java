package com.library.ms.domain.service;

import com.library.ms.domain.dto.Reponse.UserResponseDto;
import com.library.ms.domain.dto.Request.UserRequestDto;
import com.library.ms.domain.entity.UserEntity;
import com.library.ms.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl() {
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        // Verifica se o e-mail já está cadastrado
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário com este e-mail.");
        }

        // Mapeando o DTO para a entidade
        UserEntity userEntity = new UserEntity();
        userEntity.setNome(userRequestDto.getNome());
        userEntity.setEmail(userRequestDto.getEmail());

        // Salvando a senha diretamente (sem criptografia)
        userEntity.setSenha(userRequestDto.getSenha());

        // Salvando o usuário
        UserEntity savedUser = userRepository.save(userEntity);

        // Criando e retornando o DTO de resposta
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setNome(savedUser.getNome());
        userResponseDto.setEmail(savedUser.getEmail());

        return userResponseDto;
    }


    @Override
    public String excluirUser(Integer id) {
        // Busca o usuário pelo ID
        Optional<UserEntity> usuario = userRepository.findById(id);

        if (usuario.isEmpty()) {
            return "Usuário não encontrado!";  // Caso o usuário não exista
        }

        // (Opcional) Aqui você pode verificar se o usuário está associado a avaliações, pedidos, etc.
        // Exemplo:
        // long avaliacoes = avaliacaoRepository.countByUsuario(usuario.get());
        // if (avaliacoes > 0) {
        //     return "Não é possível excluir. Este usuário possui avaliações associadas.";
        // }

        // Excluir o usuário
        userRepository.delete(usuario.get());

        return "Usuário excluído com sucesso!";
    }

    @Override
    public String excluirUser(Long id) {
        return "";
    }

}
