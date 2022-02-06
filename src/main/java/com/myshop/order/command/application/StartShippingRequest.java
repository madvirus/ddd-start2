package com.myshop.order.command.application;

public class StartShippingRequest {
    private String orderNumber;
    private long version;

    protected StartShippingRequest() {
    }

    public StartShippingRequest(String orderNumber, long version) {
        this.orderNumber = orderNumber;
        this.version = version;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public long getVersion() {
        return version;
    }
}
