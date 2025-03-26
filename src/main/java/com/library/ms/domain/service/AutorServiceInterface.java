package com.library.ms.domain.service;


import com.library.ms.domain.dto.Reponse.AutorResponseDto;
import com.library.ms.domain.dto.Reponse.UserResponseDto;
import com.library.ms.domain.dto.Request.AutorRequestDto;
import com.library.ms.domain.entity.AutorEntity;
import com.library.ms.domain.entity.EditoraEntity;

import java.util.List;

public interface AutorServiceInterface {
    AutorResponseDto save(AutorRequestDto autorRequestDto);

    List<AutorEntity> listarTodosAutores();

    List<AutorEntity> listarAutores();

    String excluirAutor(Integer id);
}
