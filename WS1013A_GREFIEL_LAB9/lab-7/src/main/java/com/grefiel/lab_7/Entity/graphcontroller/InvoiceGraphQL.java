package com.grefiel.lab_7.Entity.graphcontroller;

import com.alabado.lab7.entity.Invoice;
import com.alabado.lab7.service.InvoiceService;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class InvoiceGraphQL {

    private final InvoiceService service;

    public InvoiceGraphQL(InvoiceService service) {
        this.service = service;
    }

    // QUERIES
    @QueryMapping
    public List<Invoice> invoices() {
        return service.getAll();
    }

    @QueryMapping
    public Invoice invoice(@Argument Long id) {
        return service.getById(id);
    }

    // MUTATION
    @MutationMapping
    public Invoice addInvoice(@Argument Long customerId, @Argument List<Long> productIds) {
        return service.add(customerId, productIds);
    }
}