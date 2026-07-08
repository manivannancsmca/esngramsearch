package com.esngramsearch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esngramsearch.model.Product;
import com.esngramsearch.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @GetMapping("/suggest")
    public ResponseEntity<List<Product>> getSuggestions(@RequestParam String query) {
        return ResponseEntity.ok(productService.suggestProducts(query));
    }
}