package com.library.ms.domain.service;


import com.library.ms.domain.dto.Reponse.AutorResponseDto;
import com.library.ms.domain.dto.Reponse.EditoraResponseDto;
import com.library.ms.domain.dto.Request.AutorRequestDto;
import com.library.ms.domain.dto.Request.EditoraRequestDto;
import com.library.ms.domain.entity.AutorEntity;
import com.library.ms.domain.entity.EditoraEntity;

import java.util.List;

public interface EditoraServiceInterface {
    EditoraResponseDto save(EditoraRequestDto editoraRequestDto);

    List<EditoraEntity> listarTodosEditoras();}
