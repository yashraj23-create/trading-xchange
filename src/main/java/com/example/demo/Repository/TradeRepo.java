package com.example.demo.Repository;

import com.example.demo.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TradeRepo extends JpaRepository<Trade, UUID> {
}
