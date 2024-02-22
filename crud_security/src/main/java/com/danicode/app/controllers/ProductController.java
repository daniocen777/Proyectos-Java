package com.danicode.app.controllers;

import com.danicode.app.entities.Product;
import com.danicode.app.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService service;

    // Validaciones personalozadas
    // @Autowired
    // private ProductValidation validation;

    @GetMapping
    public List<Product> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Optional<Product> productOptional = service.findById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // BindingResult result => justo a la derecha del Body
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        // validation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        Product newProduct = service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {
        // validation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        Optional<Product> productOptional = service.update(id, product);
        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> productOptional = service.delete(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
