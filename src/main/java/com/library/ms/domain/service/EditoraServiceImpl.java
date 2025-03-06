

package com.library.ms.domain.service;

import com.library.ms.domain.dto.Reponse.EditoraResponseDto;
import com.library.ms.domain.dto.Request.EditoraRequestDto;
import com.library.ms.domain.entity.EditoraEntity;
import com.library.ms.domain.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraServiceImpl implements EditoraServiceInterface {
    @Autowired
    private EditoraRepository editoraRepository;


    public EditoraResponseDto save(EditoraRequestDto editoraRequestDto) {
        // Converter DTO para entidade
        EditoraEntity editoraEntity = new EditoraEntity();
        editoraEntity.setNome(editoraRequestDto.getNome());

        // Salvar no repositório
        EditoraEntity savedEditora = editoraRepository.save(editoraEntity);

        // Converter entidade salva para DTO de resposta e retornar
        return new EditoraResponseDto(savedEditora.getId(), savedEditora.getNome());
    }

    @Override
    public List<EditoraEntity> listarTodosEditoras() {
        return editoraRepository.findAll();
    }



}
