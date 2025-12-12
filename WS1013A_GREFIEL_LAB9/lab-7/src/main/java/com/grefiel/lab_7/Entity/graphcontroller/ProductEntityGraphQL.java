package com.grefiel.lab_7.Entity.graphcontroller;

import com.alabado.lab7.entity.Product;
import com.alabado.lab7.service.ProductService;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductEntityGraphQL {

    private final ProductService service;

    public ProductEntityGraphQL(ProductService service) {
        this.service = service;
    }

    // QUERIES
    @QueryMapping
    public List<Product> products() {
        return service.getAll();
    }

    @QueryMapping
    public Product product(@Argument Long id) {
        return service.getById(id);
    }

    // MUTATION
    @MutationMapping
    public Product addProduct(@Argument String name) {
        return service.add(name);
    }
}