package com.example.demo.dto;

import com.example.demo.entity.OrderSide;
import com.example.demo.entity.OrderType;
import com.example.demo.entity.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private UUID id;

    private UUID userId;

    private UUID stockId;

    private OrderSide side;

    private OrderType orderType;

    private BigDecimal price;

    private Long quantity;

    private Long remainingQty;

    private OrderStatus status;

    private Instant createdAt;
}