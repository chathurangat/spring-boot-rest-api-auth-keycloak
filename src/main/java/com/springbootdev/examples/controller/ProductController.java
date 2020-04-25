package com.springbootdev.examples.controller;

import com.springbootdev.examples.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        return List.of(
                Product.builder().id(1L).name("product 1").build(),
                Product.builder().id(2L).name("product 2").build()
        );
    }
}
