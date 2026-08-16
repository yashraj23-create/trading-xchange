package com.example.demo.Repository;

import com.example.demo.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepo extends JpaRepository<Wallet , UUID> {
}
