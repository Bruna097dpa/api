package br.com.bfs.api.services;

import br.com.bfs.api.model.Category;
import br.com.bfs.api.model.Product;
import br.com.bfs.api.model.Review;
import br.com.bfs.api.repository.CategoryRepository;
import br.com.bfs.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repositorio;

    @Autowired
    CategoryRepository categoryRepository;




    public List<Product> retornarTodosOsProdutos() {
List<Product> products=repositorio.findAll();
for (Product product:products) {calcularNotaDoProduto(product);}
        return products;
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



    public Product calcularNotaDoProduto(Product product){
        double nota = 0;
        if( !CollectionUtils.isEmpty(product.getReviews())){
            for( Review review : product.getReviews() ){
                nota += review.getRate();
            }

            nota /= product.getReviews().size();
        }
        product.setReviewRate(nota);
        return product;
    }
}


