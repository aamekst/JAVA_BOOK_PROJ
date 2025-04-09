

package com.library.ms.domain.service;

import com.library.ms.domain.dto.Reponse.EditoraResponseDto;
import com.library.ms.domain.dto.Request.EditoraRequestDto;
import com.library.ms.domain.entity.EditoraEntity;
import com.library.ms.domain.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public EditoraResponseDto atualizar(Integer id, EditoraRequestDto editoraRequestDto) {
        if (id == null) {
            throw new IllegalArgumentException("O ID da editora não pode ser nulo.");
        }

        EditoraEntity exist = editoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada com o ID: " + id));

        exist.setNome(editoraRequestDto.getNome());

        EditoraEntity savedEditora = editoraRepository.save(exist);

        return new EditoraResponseDto(savedEditora.getId(), savedEditora.getNome());
    }

    @Override
    public EditoraEntity buscarPorId(Integer id) {
        return editoraRepository.findById(id).orElse(null);
    }



}
