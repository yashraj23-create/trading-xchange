package com.example.demo.Mapper;

import com.example.demo.dto.StockResponse;
import com.example.demo.entity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockResponse toResponse(Stock stock);

    Stock toEntity(StockResponse stockResponse);
}