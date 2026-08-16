package com.example.demo.Repository;

import com.example.demo.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockRepo extends JpaRepository<Stock, UUID> {
}
