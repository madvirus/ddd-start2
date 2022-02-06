package com.myshop.order.query.dto;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(OrderSummary.class)
public class OrderSummary_ {
    public static volatile SingularAttribute<OrderSummary, String> number;
    public static volatile SingularAttribute<OrderSummary, Long> version;
    public static volatile SingularAttribute<OrderSummary, String> ordererId;
    public static volatile SingularAttribute<OrderSummary, String> ordererName;
    public static volatile SingularAttribute<OrderSummary, Integer> totalAmounts;
    public static volatile SingularAttribute<OrderSummary, String> receiverName;
    public static volatile SingularAttribute<OrderSummary, String> state;
    public static volatile SingularAttribute<OrderSummary, LocalDateTime> orderDate;
    public static volatile SingularAttribute<OrderSummary, String> productId;
    public static volatile SingularAttribute<OrderSummary, String> productName;
}
