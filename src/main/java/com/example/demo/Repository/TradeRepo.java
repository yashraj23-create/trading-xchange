package com.example.demo.Repository;

import com.example.demo.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TradeRepo extends JpaRepository<Trade, UUID> {
}
