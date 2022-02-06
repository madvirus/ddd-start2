package com.myshop.order.query.application;

import com.myshop.catalog.query.product.ProductData;
import com.myshop.order.command.domain.OrderLine;

public class OrderLineDetail {

    private final String productId;
    private final int price;
    private final int quantity;
    private final int amounts;
    private final String productName;
    private final String productImagePath;

    public OrderLineDetail(OrderLine orderLine, ProductData product) {
        productId = orderLine.getProductId().getId();
        price = orderLine.getPrice().getValue();
        quantity = orderLine.getQuantity();
        amounts = orderLine.getAmounts().getValue();
        productName = product.getName();
        productImagePath = product.getFirstIamgeThumbnailPath();
    }

    public String getProductId() {
        return productId;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAmounts() {
        return amounts;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImagePath() {
        return productImagePath;
    }
}
