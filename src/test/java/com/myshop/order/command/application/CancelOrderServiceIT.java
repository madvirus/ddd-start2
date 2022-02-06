package com.myshop.order.command.application;

import com.myshop.order.command.domain.Canceller;
import com.myshop.order.command.domain.OrderNo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("classpath:shop-init-test.sql")
class CancelOrderServiceIT {
    @Autowired
    private CancelOrderService cancelOrderService;

    @Test
    void cancel() {
        cancelOrderService.cancel(OrderNo.of("ORDER-001"), Canceller.of("user1"));
    }
}