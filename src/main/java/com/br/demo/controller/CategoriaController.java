package com.br.demo.controller;
import com.br.demo.dto.CategoriaDTO;

import com.br.demo.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")


public class CategoriaController {
    private final CategoriaService categoriaService;
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    //Listar todas as categorias
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
    //Criar uma nova categoria
    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody CategoriaDTO categoriaDTO){
        return ResponseEntity.ok(categoriaService.criarCategoria(categoriaDTO));
    }
    //Buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }
    //Atualizar categoria por ID
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO){
        return ResponseEntity.ok(categoriaService.atualizarCategoria(id, categoriaDTO));
    }
    //Deletar categoria por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCategoria(@PathVariable Long id){
        categoriaService.excluirCategoria(id);
        return ResponseEntity.noContent().build();
    }



}
