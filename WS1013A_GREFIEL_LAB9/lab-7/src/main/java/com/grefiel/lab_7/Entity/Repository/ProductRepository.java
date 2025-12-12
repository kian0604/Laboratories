package com.grefiel.lab_7.Entity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alabado.lab7.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

