package com.example.controllers;

import com.example.requests.Product;
import com.example.requests.ProductMessage;
import com.example.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api")
@Service
public class DemoController {
    ProductService productService;

    @Autowired
    public DemoController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        log.info("[DemoController]: add new product = " + product.toString());
        this.productService.sendMessage(new ProductMessage(product, "add"));
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable String id) {
        log.info("[DemoController]: delete product id = " + id);
        Product p = new Product();
        p.setId(id);
        this.productService.sendMessage(new ProductMessage(p, "delete"));
    }
}
