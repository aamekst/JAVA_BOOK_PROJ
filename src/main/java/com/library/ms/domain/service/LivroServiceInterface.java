package com.library.ms.domain.service;

import com.library.ms.domain.dto.Reponse.LivroResponseDto;
import com.library.ms.domain.dto.Request.LivroRequestDto;

public interface LivroServiceInterface {
    LivroResponseDto save(LivroRequestDto livroResponseDto);

}
