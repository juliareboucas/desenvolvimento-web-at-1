package com.br.demo.repository;
import com.br.demo.model.Categoria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class CategoriaRepository {

    private final List<Categoria> categorias = new ArrayList<>();
    private Long nextId = 1L;

    //Listar todas as categorias
    public List<Categoria> findAll(){

        return new ArrayList<>(categorias);
    }
    //Buscar uma categoria por ID
    public Optional<Categoria> findById(Long id){
        return categorias.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
    //Adicionar uma nova categoria
    public Categoria save(Categoria categoria){
        categoria.setId(nextId++);
        categorias.add(categoria);
        return categoria;
    }
    //Atualizar uma categoria existente
    public Categoria update(Categoria categoria){
        return categorias.stream()
                .filter(p -> p.getId().equals(categoria.getId()))
                .findFirst()
                .map(p -> {
                    p.setNome(categoria.getNome());
                    if (categoria.getDescricao() != null) {
                        p.setDescricao(categoria.getDescricao());
                    }
                    return p;
                })
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada para atualização"));
    }
    //Remover uma categoria
    public void deleteById(Long id){
        categorias.removeIf(p -> p.getId().equals(id));
    }
}
