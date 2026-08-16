package com.example.demo.Repository;

import com.example.demo.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletTransactionRepo extends JpaRepository<WalletTransaction, UUID> {

}
