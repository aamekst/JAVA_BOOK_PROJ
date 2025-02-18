

package com.library.ms.domain.service;

import com.library.ms.domain.dto.Reponse.AutorResponseDto;
import com.library.ms.domain.dto.Request.AutorRequestDto;
import com.library.ms.domain.entity.AutorEntity;
import com.library.ms.domain.entity.LivroEntity;
import com.library.ms.domain.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImpl implements AutorServiceInterface {
    @Autowired
    private AutorRepository autorRepository;


    public AutorResponseDto save(AutorRequestDto autorRequestDto) {
        // Converter DTO para entidade
        AutorEntity autorEntity = new AutorEntity();
        autorEntity.setNome(autorRequestDto.getNome());

        // Salvar no repositório
        AutorEntity savedAutor = autorRepository.save(autorEntity);

        // Converter entidade salva para DTO de resposta e retornar
        return new AutorResponseDto(savedAutor.getId(), savedAutor.getNome());
    }

    @Override
    public List<AutorEntity> listarTodosAutores() {
        return autorRepository.findAll();  // Retorna todos os autores da base de dados
    }


}
