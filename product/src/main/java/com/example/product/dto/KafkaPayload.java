package com.example.product.dto;

import lombok.Data;

@Data
public class KafkaPayload {
    private Long productId;
    private Long id;
    private int stock;
    private long createdDate;
}