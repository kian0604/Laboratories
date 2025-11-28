package com.grefiel.lab_7.graphcontroller;

import com.grefiel.lab_7.Product;
import com.grefiel.lab_7.ProductService;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductGraphQLController {

    private final ProductService service;

    public ProductGraphQLController(ProductService service) {
        this.service = service;
    }

    // QUERIES ------------------------------

    @QueryMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @QueryMapping
    public Product getProductById(@Argument Long id) {
        return service.getProductById(id);
    }

    // MUTATIONS ----------------------------

    @MutationMapping
    public Product addProduct(@Argument ProductInput product) {
        Product p = new Product(null, product.getName(), product.getPrice());
        return service.addProduct(p);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductInput product) {
        Product updated = new Product(id, product.getName(), product.getPrice());
        return service.updateProduct(id, updated);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        return service.deleteProduct(id);
    }
}