package br.com.bfs.api.controller;

import br.com.bfs.api.model.Category;
import br.com.bfs.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    public void adicionarCategoria(@RequestBody Category category) {
        categoryService.salvarCategoria(category);
    }
    @GetMapping("")
    public List<Category> retornarTodas(){
        return categoryService.retornarTodasAsCategorias();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> buscarPorId(@PathVariable Long id){
        try {
            Category category = categoryService.retornarCategoriaPorId(id);
            return new ResponseEntity<>(category,HttpStatus.OK);
        }catch  (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/{id}")
    public void removerCategoria(@PathVariable Long id) {
        categoryService.removeCategroia(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> atualizarCategoria(@RequestBody Category category, @PathVariable Long id){
        try {
            Category categoriaNoBancoDeDados = categoryService.retornarCategoriaPorId(id);
            category.setId(id);
            categoryService.salvarCategoria(category);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}



