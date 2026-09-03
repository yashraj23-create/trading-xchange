
package com.example.demo.matching;


import java.math.BigDecimal;
import java.util.*;

public class OrderBook {
    private final TreeMap<BigDecimal, LinkedList<Order>> buySide = new TreeMap<>(Comparator.reverseOrder());
    private final TreeMap<BigDecimal, LinkedList<Order>> sellSide = new TreeMap<>();
    private final Map<String, Order> ordersById = new HashMap<>();

    public List<Trade> submit(Order incoming) {
        List<Trade> trades = new ArrayList<>();
        ordersById.put(incoming.getId(), incoming);

        TreeMap<BigDecimal, LinkedList<Order>> opposite =
                incoming.getSide() == Order.Side.BUY ? sellSide : buySide;

        while (!incoming.isFilled() && !opposite.isEmpty()) {
            Map.Entry<BigDecimal, LinkedList<Order>> best = opposite.firstEntry();
            BigDecimal bestPrice = best.getKey();

            if (incoming.getType() == Order.Type.LIMIT && !priceCrosses(incoming, bestPrice)) {
                break;
            }

            LinkedList<Order> queue = best.getValue();
            Order resting = queue.peekFirst();

            long matchQty = Math.min(incoming.remainingQuantity(), resting.remainingQuantity());
            resting.fill(matchQty);
            incoming.fill(matchQty);

            Trade trade = incoming.getSide() == Order.Side.BUY
                    ? new Trade(incoming.getId(), resting.getId(), bestPrice, matchQty)
                    : new Trade(resting.getId(), incoming.getId(), bestPrice, matchQty);
            trades.add(trade);

            if (resting.isFilled()) {
                queue.pollFirst();
                ordersById.remove(resting.getId());
                if (queue.isEmpty()) opposite.remove(bestPrice);
            }
        }

        if (!incoming.isFilled()) {
            if (incoming.getType() == Order.Type.LIMIT) {
                TreeMap<BigDecimal, LinkedList<Order>> own =
                        incoming.getSide() == Order.Side.BUY ? buySide : sellSide;
                own.computeIfAbsent(incoming.getPrice(), p -> new LinkedList<>()).addLast(incoming);
            } else {
                ordersById.remove(incoming.getId()); // market remainder just dies
            }
        }

        return trades;
    }

    public boolean cancel(String orderId) {
        Order order = ordersById.get(orderId);
        if (order == null) return false;
        TreeMap<BigDecimal, LinkedList<Order>> side =
                order.getSide() == Order.Side.BUY ? buySide : sellSide;
        LinkedList<Order> queue = side.get(order.getPrice());
        if (queue == null) return false;
        boolean removed = queue.remove(order);
        if (queue.isEmpty()) side.remove(order.getPrice());
        if (removed) {
            order.cancel();
            ordersById.remove(orderId);
        }
        return removed;
    }

    private boolean priceCrosses(Order incoming, BigDecimal bestOppositePrice) {
        return incoming.getSide() == Order.Side.BUY
                ? incoming.getPrice().compareTo(bestOppositePrice) >= 0
                : incoming.getPrice().compareTo(bestOppositePrice) <= 0;
    }

    public BigDecimal bestBid() { return buySide.isEmpty() ? null : buySide.firstKey(); }
    public BigDecimal bestAsk() { return sellSide.isEmpty() ? null : sellSide.firstKey(); }
}