package br.com.bfs.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "products")
public class Product extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer stock = 0;

    private Double price = 0.0;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Collection<Category> categories;

    @OneToMany(mappedBy ="product")
    @JsonManagedReference
    private Collection<Review> reviews;
    

    private Double reviewRate;

    public Product calcularNotaDoProduto(Product product){
        double nota = 0;
        if( !CollectionUtils.isEmpty(product.getReviews())){
            for( Review review : product.getReviews() ){
                nota += review.getRate();
            }

            nota /= (double)product.getReviews().size();
        }
        product.setReviewRate(nota);
        return product;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    public void setReviewRate(double reviewRate) {
        this.reviewRate = reviewRate;
    }

    public double getReviewRate() {
        return reviewRate;
    }
}