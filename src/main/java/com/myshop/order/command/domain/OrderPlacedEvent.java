package com.myshop.order.command.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrderPlacedEvent {
    private String number;
    private Orderer orderer;
    private List<OrderLine> orderLines;
    private LocalDateTime orderDate;

    private OrderPlacedEvent() {
    }

    public OrderPlacedEvent(String number, Orderer orderer, List<OrderLine> orderLines, LocalDateTime orderDate) {
        this.number = number;
        this.orderer = orderer;
        this.orderLines = orderLines;
        this.orderDate = orderDate;
    }

    public String getNumber() {
        return number;
    }

    public Orderer getOrderer() {
        return orderer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
