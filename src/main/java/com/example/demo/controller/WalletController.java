package com.example.demo.controller;


import com.example.demo.dto.DepositRequest;
import com.example.demo.dto.Withdraw;
import com.example.demo.security.UserPrinciple;
import com.example.demo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(
            @RequestBody DepositRequest request) {

        UserPrinciple userPrinciple = (UserPrinciple) Objects.
                requireNonNull(SecurityContextHolder.getContext().
                getAuthentication()).getPrincipal();

        assert userPrinciple != null;
        walletService.deposit(request.getAmount(), userPrinciple.GetUser());

        return ResponseEntity.ok("Money Deposited");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Withdraw withdraw){

        UserPrinciple userPrinciple = (UserPrinciple) Objects.
                requireNonNull(SecurityContextHolder.getContext().
                        getAuthentication()).getPrincipal();

        assert userPrinciple != null;
        walletService.Withdraw(userPrinciple.GetUser(), withdraw.getWithdraw());

        return ResponseEntity.ok("Money Withdrawn");
    }

}
