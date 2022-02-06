package com.myshop.order.command.domain;

public class ShippingInfoChangedEvent {
    private final OrderNo number;
    private final ShippingInfo newShippingInfo;
    private long timestamp;

    public ShippingInfoChangedEvent(OrderNo number, ShippingInfo newShippingInfo) {
        super();
        this.number = number;
        this.newShippingInfo = newShippingInfo;
        this.timestamp = System.currentTimeMillis();
    }

    public OrderNo getNumber() {
        return number;
    }

    public ShippingInfo getNewShippingInfo() {
        return newShippingInfo;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
