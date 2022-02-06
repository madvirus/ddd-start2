package com.myshop.order.query.application;

import com.myshop.order.command.domain.Order;
import com.myshop.order.command.domain.OrderState;
import com.myshop.order.command.domain.Orderer;
import com.myshop.order.command.domain.ShippingInfo;

import java.util.List;

public class OrderDetail {

    private final String number;
    private long version;
    private final Orderer orderer;
    private final ShippingInfo shippingInfo;
    private final OrderState state;
    private final int totalAmounts;
    private List<OrderLineDetail> orderLines;
    private final boolean notYetShipped;

    public OrderDetail(Order order, List<OrderLineDetail> orderLines) {
        this.orderLines = orderLines;
        number = order.getNumber().getNumber();
        version = order.getVersion();
        orderer = order.getOrderer();
        shippingInfo = order.getShippingInfo();
        state = order.getState();
        notYetShipped = order.isNotYetShipped();
        totalAmounts = order.getTotalAmounts().getValue();
    }

    public String getNumber() {
        return number;
    }

    public long getVersion() {
        return version;
    }

    public Orderer getOrderer() {
        return orderer;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public OrderState getState() {
        return state;
    }

    public int getTotalAmounts() {
        return totalAmounts;
    }

    public List<OrderLineDetail> getOrderLines() {
        return orderLines;
    }

    public boolean isNotYetShipped() {
        return notYetShipped;
    }
}
