package com.grefiel.lab_7;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {

    private Map<Long, Product> products = new HashMap<>();
    private long nextId = 4;

    public ProductService() {
        products.put(1L, new Product(1L, "Laptop Pro", 55000.00));
        products.put(2L, new Product(2L, "Mechanical Keyboard", 2500.00));
        products.put(3L, new Product(3L, "Gaming Mouse", 1200.00));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(Long id) {
        return products.get(id);
    }

    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.put(product.getId(), product);
        return product;
    }

    public Product updateProduct(Long id, Product updated) {
        if (!products.containsKey(id)) {
            return null;
        }
        updated.setId(id);
        products.put(id, updated);
        return updated;
    }

    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }
}