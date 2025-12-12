package com.grefiel.lab_7.Entity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alabado.lab7.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

