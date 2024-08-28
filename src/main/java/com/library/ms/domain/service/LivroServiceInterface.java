package com.library.ms.domain.service;

import com.library.ms.domain.dto.Reponse.LivroResponseDto;
import com.library.ms.domain.dto.Request.LivroRequestDto;
import com.library.ms.domain.entity.LivroEntity;

public interface LivroServiceInterface {
    LivroResponseDto save(LivroRequestDto livroResponseDto);

    LivroEntity buscarPorId(Integer id);
}
