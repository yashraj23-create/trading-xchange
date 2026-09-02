package com.example.demo.matching;

import java.math.BigDecimal;

public class Trade {

    public final String buyOrderId;
    public final String sellOrderId;
    public final BigDecimal price;
    public final long quantity;

    public Trade(String buyOrderId, String sellOrderId, BigDecimal price, long quantity) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Trade{buy=" + buyOrderId + ", sell=" + sellOrderId + ", price=" + price + ", qty=" + quantity + "}";
    }

}
