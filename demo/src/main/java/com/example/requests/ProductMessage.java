package com.example.requests;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductMessage extends Product implements Serializable {
    String action;

    public ProductMessage() {

    }

    public ProductMessage(Product p, String action) {
        this.setId(p.getId());
        this.setName(p.getName());
        this.setPrice(p.getPrice());
        this.action = action;
    }
}
