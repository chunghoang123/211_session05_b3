package org.example.b1.controller;

import jakarta.validation.Valid;
import org.example.b1.entity.Product;
import org.example.b1.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // GET all
    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // GET by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().<Product>build());
    }

    // POST
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @Valid @RequestBody Product product) {

        Product saved = repository.save(product);

        return ResponseEntity.status(201).body(saved);
    }

}