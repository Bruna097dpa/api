package br.com.bfs.api.services;

import br.com.bfs.api.model.Review;
import br.com.bfs.api.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository repositorio;
    @Autowired
    ProductService productService;

    public List<Review> retornarTodasAsReviews() {
        return repositorio.findAll();
    }

    public Review RetornarReviewPorId(Long id) {
        return repositorio.findById(id).orElseThrow();
    }

    public void save(Review entity){
        entity.setProduct(productService.retornarProdutoPorId(entity.getProduct().getId()));
        repositorio.save(entity);
    }

    public void removeReview(Long id) {
        repositorio.deleteById(id);
    }

}