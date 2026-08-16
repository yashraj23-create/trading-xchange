package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStockRequest {

    @NotBlank
    private String symbol;

    @NotBlank
    private String companyName;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal currentPrice;

    @NotNull
    @Positive
    private Long totalShares;
}