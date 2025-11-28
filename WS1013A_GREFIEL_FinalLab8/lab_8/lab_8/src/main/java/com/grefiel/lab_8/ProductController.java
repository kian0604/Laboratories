package com.grefiel.lab_7;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<com.grefiel.lab_7.Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<com.grefiel.lab_7.Product> getProductById(@PathVariable Long id) {
        com.grefiel.lab_7.Product p = service.getProductById(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<com.grefiel.lab_7.Product> addProduct(@RequestBody com.grefiel.lab_7.Product product) {
        com.grefiel.lab_7.Product created = service.addProduct(product);
        return ResponseEntity.status(201).body(created);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<com.grefiel.lab_7.Product> updateProduct(
            @PathVariable Long id,
            @RequestBody com.grefiel.lab_7.Product product) {

        com.grefiel.lab_7.Product updated = service.updateProduct(id, product);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean removed = service.deleteProduct(id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}