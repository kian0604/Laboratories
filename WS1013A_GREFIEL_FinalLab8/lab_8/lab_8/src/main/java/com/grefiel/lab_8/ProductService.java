package com.grefiel.lab_7;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {

    private Map<Long, com.grefiel.lab_7.Product> products = new HashMap<>();
    private long nextId = 4;

    public ProductService() {
        products.put(1L, new com.grefiel.lab_7.Product(1L, "Laptop Pro", 55000.00));
        products.put(2L, new com.grefiel.lab_7.Product(2L, "Mechanical Keyboard", 2500.00));
        products.put(3L, new com.grefiel.lab_7.Product(3L, "Gaming Mouse", 1200.00));
    }

    public List<com.grefiel.lab_7.Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public com.grefiel.lab_7.Product getProductById(Long id) {
        return products.get(id);
    }

    public com.grefiel.lab_7.Product addProduct(com.grefiel.lab_7.Product product) {
        product.setId(nextId++);
        products.put(product.getId(), product);
        return product;
    }

    public com.grefiel.lab_7.Product updateProduct(Long id, com.grefiel.lab_7.Product updated) {
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