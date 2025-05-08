package com.library.ms.domain.service;


import com.library.ms.domain.dto.Reponse.UserResponseDto;
import com.library.ms.domain.dto.Request.UserRequestDto;

public interface UserServiceInterface {
    UserResponseDto save(UserRequestDto userRequestDto);

    String excluirUser(Integer id);

    String excluirUser(Long id);
}
