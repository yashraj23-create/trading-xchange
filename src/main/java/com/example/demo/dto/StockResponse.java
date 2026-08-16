package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {

    private UUID id;

    private String symbol;

    private String companyName;

    private BigDecimal currentPrice;

    private Long totalShares;

    private String status;
}