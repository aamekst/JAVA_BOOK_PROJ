

package com.library.ms.domain.service;

import com.library.ms.domain.dto.Reponse.AutorResponseDto;
import com.library.ms.domain.dto.Request.AutorRequestDto;
import com.library.ms.domain.entity.AutorEntity;
import com.library.ms.domain.repository.AutorRepository;
import com.library.ms.domain.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorServiceInterface {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;


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
        return autorRepository.findAll();  // Corrigido para retornar todos os autores do banco
    }

    // Método para listar autores
    @Override
    public List<AutorEntity> listarAutores() {
        return autorRepository.findAll();  // Retorna todos os autores
    }

    // Método para excluir autor
    @Override
    public String excluirAutor(Integer id) {
        // Busca o autor pelo ID
        Optional<AutorEntity> autor = autorRepository.findById(id);

        if (autor.isEmpty()) {
            return "Autor não encontrado!";  // Caso o autor não seja encontrado
        }

        // Verificar se o autor está associado a algum livro
        long livrosAssociados = livroRepository.countByAutor(autor.get());

        if (livrosAssociados > 0) {
            return "Não é possível excluir. Este autor está associado a livros.";  // Se o autor estiver associado a algum livro, não permite excluir
        }

        // Excluir o autor
        autorRepository.delete(autor.get());

        return "Autor excluído com sucesso!";  // Retorna sucesso se o autor foi excluído
    }
}
