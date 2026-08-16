package com.example.demo.Mapper;

import com.example.demo.dto.CreateOrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // CreateOrderRequest -> Order
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "stock", ignore = true)
    @Mapping(target = "remainingQty", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Order toEntity(CreateOrderRequest request);

    // Order -> OrderResponse
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "stockId", source = "stock.id")
    OrderResponse toResponse(Order order);
}