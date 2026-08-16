package com.example.demo.dto;

import com.example.demo.entity.OrderSide;
import com.example.demo.entity.OrderType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotNull
    private UUID stockId;

    @NotNull
    private OrderSide side;

    @NotNull
    private OrderType orderType;

    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @NotNull
    @Positive
    private Long quantity;
}