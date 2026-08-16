package com.example.demo.Repository;

import com.example.demo.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PortfolioRepo extends JpaRepository<Portfolio, UUID> {
}
