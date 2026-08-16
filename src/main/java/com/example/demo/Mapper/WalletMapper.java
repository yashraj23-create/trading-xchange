package com.example.demo.Mapper;

import com.example.demo.dto.WalletResponse;
import com.example.demo.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface WalletMapper {

    @Mapping(target = "userId", source = "user.id")
    WalletResponse toWalletResponse(Wallet wallet);

    Wallet toWalletEntity(WalletResponse walletResponse);
}
