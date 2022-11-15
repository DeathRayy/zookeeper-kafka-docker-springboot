package com.example.controllers;

import com.example.kafka.product.ProductProducer;
import com.example.requests.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("api")
@Service
public class DemoController {
    ProductProducer productProducer;

    @Autowired
    public DemoController(ProductProducer productProducer) {
        this.productProducer = productProducer;
    }

    @GetMapping("/products")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        productProducer.send(new Product(), "SaveReview");
        return String.format("Hello %s!", name);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        log.info("[DemoController]: add new product = ", product.toString());
        productProducer.send(product, "SaveReview");
        return ResponseEntity.ok(product);
    }
}
