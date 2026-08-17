package com.example.demo.Repository;

import com.example.demo.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WalletTransactionRepo extends JpaRepository<WalletTransaction, UUID> {

}
