package com.br.demo.service;
import com.br.demo.dto.CategoriaDTO;
import com.br.demo.model.Categoria;
import com.br.demo.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.stream.Collectors;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    //Listar todas as categorias
    public List<CategoriaDTO> listarCategorias(){
        return categoriaRepository.findAll().stream()
                .map(p -> new CategoriaDTO(p.getId(), p.getNome(), p.getDescricao()))
                .collect(Collectors.toList());
    }
    //Buscar categoria por ID
    public CategoriaDTO buscarPorId(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }
    //Criar uma categoria
    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO){
        Categoria novaCategoria = new Categoria(categoriaDTO.getId(), categoriaDTO.getNome(), categoriaDTO.getDescricao());
        Categoria categoriaSalva = categoriaRepository.save(novaCategoria);
        return new CategoriaDTO(categoriaSalva.getId(), categoriaSalva.getNome(), categoriaSalva.getDescricao());
    }
    //Atualizar uma categoria
    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO categoriaDTO){
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoriaExistente.setNome(categoriaDTO.getNome());
        categoriaExistente.setDescricao(categoriaDTO.getDescricao());


        Categoria categoriaAtualizada = categoriaRepository.update(categoriaExistente);
        return new CategoriaDTO(categoriaAtualizada.getId(), categoriaAtualizada.getNome(), categoriaAtualizada.getDescricao());

    }
    //Deletar uma categoria
    public void excluirCategoria(Long id){
        categoriaRepository.deleteById(id);
    }

}
