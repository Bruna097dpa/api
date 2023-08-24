package br.com.bfs.api.controller;

import br.com.bfs.api.model.Review;
import br.com.bfs.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/")
    public void adicionarReview(@RequestBody Review review){
        reviewService.save(review);
    }

    @DeleteMapping("/{id}")
    public void removerReview(@PathVariable Long id){
        reviewService.removeReview(id);
    }

}