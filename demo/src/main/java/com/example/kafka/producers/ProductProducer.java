package com.example.kafka.producers;

import com.example.requests.Product;
import com.example.requests.ProductMessage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.Serializable;

@Slf4j
@NoArgsConstructor
@Component
public class ProductProducer {
    final String productTopic = "product";

    private KafkaTemplate<String, Serializable> kafkaTemplate;

    @Autowired
    public ProductProducer(KafkaTemplate<String, Serializable> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(ProductMessage message) {
        ListenableFuture<SendResult<String, Serializable>> future = kafkaTemplate.send(productTopic, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Serializable>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message = {} dut to: {}", message.toString(), ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Serializable> result) {
                log.info("Message sent successfully with offset = {}", result.getRecordMetadata().offset());
            }
        });
    }

}
