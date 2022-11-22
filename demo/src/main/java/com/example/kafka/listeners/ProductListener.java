package com.example.kafka.listeners;

import com.example.requests.ProductMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductListener {
    @KafkaListener(topics = "product", containerFactory = "kafkaListenerContainerFactory")
    public void newProductListener(ProductMessage product) {
        log.info("Get request from Front 'save product'" + product.toString());
    }
}
