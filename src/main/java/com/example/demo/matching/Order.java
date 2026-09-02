package com.example.demo.matching;

import java.math.BigDecimal;
import java.time.Instant;

public class Order {

    public enum Side { BUY, SELL }
    public enum Type { LIMIT, MARKET }
    public enum Status { NEW, PARTIALLY_FILLED, FILLED, CANCELLED }

    private final String id;
    private final Side side;
    private final Type type;
    private final BigDecimal price;       // null for MARKET
    private final long quantity;
    private long filledQuantity;
    private Status status;
    private final Instant timestamp;

    public Order(String id, Side side, Type type, BigDecimal price, long quantity, Instant timestamp) {
        this.id = id;
        this.side = side;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.filledQuantity = 0;
        this.status = Status.NEW;
        this.timestamp = timestamp;
    }

    long remainingQuantity(){
        return quantity - filledQuantity;
    }

    public boolean isFilled() { return remainingQuantity() == 0; }


    void fill(long quantity){
        filledQuantity += quantity;
        status = isFilled() ? Status.FILLED : Status.PARTIALLY_FILLED;

    }

    public void cancel() { status = Status.CANCELLED; }


    public String getId() { return id; }
    public Side getSide() { return side; }
    public Type getType() { return type; }
    public BigDecimal getPrice() { return price; }
    public long getQuantity() { return quantity; }
    public long getFilledQuantity() { return filledQuantity; }
    public Status getStatus() { return status; }
    public Instant getTimestamp() { return timestamp; }

}
