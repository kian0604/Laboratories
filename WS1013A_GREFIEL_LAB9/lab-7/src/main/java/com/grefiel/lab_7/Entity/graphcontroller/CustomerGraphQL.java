package com.grefiel.lab_7.Entity.graphcontroller;

import com.alabado.lab7.entity.Customer;
import com.alabado.lab7.service.CustomerService;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerGraphQL {

    private final CustomerService service;

    public CustomerGraphQL(CustomerService service) {
        this.service = service;
    }

    // QUERIES
    @QueryMapping
    public List<Customer> customers() {
        return service.getAll();
    }

    @QueryMapping
    public Customer customer(@Argument Long id) {
        return service.getById(id);
    }

    // MUTATION
    @MutationMapping
    public Customer addCustomer(@Argument String name) {
        return service.add(name);
    }
}
