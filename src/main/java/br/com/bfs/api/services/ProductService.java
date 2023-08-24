package br.com.bfs.api.services;

import br.com.bfs.api.model.Category;
import br.com.bfs.api.model.Product;
import br.com.bfs.api.repository.CategoryRepository;
import br.com.bfs.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repositorio;

    @Autowired
    CategoryRepository categoryRepository;


    private Double reviewRate;

    public List<Product> retornarTodosOsProdutos() {
        return repositorio.findAll();
    }

    public Product retornarProdutoPorId(Long id) {
        return repositorio.findById(id).orElseThrow();
    }

    public void salvarProduto(Product product){
        if ( product.getCategories() != null ){
            List<Category> categoriasAAdcicionar = new ArrayList<>();
            for( Category category : product.getCategories()){
                categoriasAAdcicionar.add(categoryRepository.getReferenceById(category.getId()));
            }
            product.setCategories(categoriasAAdcicionar);
        }
        repositorio.save(product);
    }

    public void removeProduto(Long id) {
        repositorio.deleteById(id);
    }


    public List<Product> getAll() {
        List<Product> products = repositorio.findAll();
        for (Product product : products) {
            calcularNotaDoProduto(product);
        }
        return repositorio.findAll();
    }

    private void calcularNotaDoProduto(Product product) {
    }
}


