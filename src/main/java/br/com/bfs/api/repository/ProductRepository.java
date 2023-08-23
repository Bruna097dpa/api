package br.com.bfs.api.repository;

import br.com.bfs.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{

}


