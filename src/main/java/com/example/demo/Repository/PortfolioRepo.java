package com.example.demo.Repository;

import com.example.demo.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio, UUID> {
}
