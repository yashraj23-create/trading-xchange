package com.example.demo.service;

import com.example.demo.Mapper.WalletMapper;
import com.example.demo.Repository.WalletRepo;
import com.example.demo.dto.WalletResponse;
import com.example.demo.entity.User;
import com.example.demo.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {
    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private WalletRepo walletRepo;
    public void createWallet(User user){
       Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(0));
        wallet.setReservedBalance(BigDecimal.valueOf(0));
        wallet.setUser(user);
        walletRepo.save(wallet);
    }
    public void deposit(BigDecimal amount, User user){
        Wallet wallet = walletRepo.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("user not found"));

        wallet.setBalance(wallet.getBalance().add(amount));

        walletRepo.save(wallet);

    }

    public void Withdraw(User user, BigDecimal withdraw){
        Wallet wallet = walletRepo.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("user not found"));


        if (wallet.getBalance()
                .compareTo(withdraw) >= 0) {

            wallet.setBalance(wallet.getBalance().subtract(withdraw));

            walletRepo.save(wallet);

        }else throw new RuntimeException("not sufficient balance");

    }

}
