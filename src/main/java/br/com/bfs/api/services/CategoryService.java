package br.com.bfs.api.services;

import br.com.bfs.api.model.Category;
import br.com.bfs.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repositorio;

    public List<Category> retornarTodasAsCategorias() {
        return repositorio.findAll();
    }

    public Category retornarCategoriaPorId(Long id) {
        return repositorio.findById(id).orElseThrow();
    }

    public void salvarCategoria(Category categoria) {
        repositorio.save(categoria);
    }

    public void removeCategroia(Long id) {
        repositorio.deleteById(id);
    }

}




