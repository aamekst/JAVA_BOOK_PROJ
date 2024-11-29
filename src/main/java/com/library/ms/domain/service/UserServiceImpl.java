

package com.library.ms.domain.service;

import com.library.ms.domain.dto.Reponse.UserResponseDto;
import com.library.ms.domain.dto.Request.UserRequestDto;
import com.library.ms.domain.entity.UserEntity;
import com.library.ms.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl() {
    }

    public UserResponseDto save(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setNome(userRequestDto.getNome());
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setSenha(userRequestDto.getSenha());
        UserEntity savedUser = (UserEntity)this.userRepository.save(userEntity);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setNome(savedUser.getNome());
        return userResponseDto;
    }
}
