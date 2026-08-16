package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioStockResponse {
    private UUID userId;
    private List<StockResponse> stock;
    private Long quantity;
    private BigDecimal averageBuyPrice;
}
