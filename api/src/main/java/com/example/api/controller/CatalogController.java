package com.example.api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Product;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    // Sample in-memory catalog storage
    private List<Product> catalog = new ArrayList<>();

    // Endpoint to  get all products from the catalog
    @GetMapping
    public List<Product> getAllProducts() {
        return catalog;
    }

    // Endpoint to add a new product to the catalog
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product newProduct) {
        catalog.add(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }

    // Endpoint to update an existing product in the catalog
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        for (Product product : catalog) {
            if (product.getId()==id ){
                product.setName(updatedProduct.getName());
                product.setDescription(updatedProduct.getDescription());
                product.setPrice(updatedProduct.getPrice());
                return ResponseEntity.ok("Product updated successfully");
            }
        }
        return ResponseEntity.notFound().build();
    }
    // Endpoint to delete a product from the catalog
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        catalog.removeIf(product -> product.getId()==(id));
        return ResponseEntity.ok("Product deleted successfully");
    }
}