package com.myshop.order.query.application;

import com.myshop.catalog.query.product.ProductData;
import com.myshop.catalog.query.product.ProductQueryService;
import com.myshop.order.command.domain.Order;
import com.myshop.order.command.domain.OrderNo;
import com.myshop.order.command.domain.OrderRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderDetailService {
    private OrderRepository orderRepository;
    private ProductQueryService productQueryService;

    public OrderDetailService(OrderRepository orderRepository,
                              ProductQueryService productQueryService) {
        this.orderRepository = orderRepository;
        this.productQueryService = productQueryService;
    }

    @Transactional
    public Optional<OrderDetail> getOrderDetail(String orderNumber) {
        Optional<Order> orderOpt = orderRepository.findById(new OrderNo(orderNumber));
        return orderOpt.map(order -> {
            List<OrderLineDetail> orderLines = order.getOrderLines().stream()
                    .map(orderLine -> {
                        Optional<ProductData> productOpt =
                                productQueryService.getProduct(orderLine.getProductId().getId());
                        return new OrderLineDetail(orderLine, productOpt.orElse(null));
                    }).collect(Collectors.toList());
            return new OrderDetail(order, orderLines);
        });
    }
}
