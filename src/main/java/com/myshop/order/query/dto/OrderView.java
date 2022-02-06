package com.myshop.order.query.dto;

import com.myshop.member.command.domain.MemberId;
import com.myshop.order.command.domain.OrderNo;
import com.myshop.order.command.domain.OrderState;

public class OrderView {

    private final String number;
    private final OrderState state;
    private final String memberName;
    private final String memberId;
    private final String productName;

    public OrderView(OrderNo number, OrderState state, String memberName, MemberId memberId, String productName) {
        this.number = number.getNumber();
        this.state = state;
        this.memberName = memberName;
        this.memberId = memberId.getId();
        this.productName = productName;
    }

    public String getNumber() {
        return number;
    }

    public OrderState getState() {
        return state;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getProductName() {
        return productName;
    }
}
