package com.myshop.order.command.domain;

import com.myshop.catalog.command.domain.product.ProductId;
import com.myshop.common.model.Address;
import com.myshop.common.model.Money;
import com.myshop.helper.DbHelper;
import com.myshop.member.command.domain.MemberId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderRepositoryIT {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DbHelper dbHelper;

    @BeforeEach
    void setUp() {
        dbHelper.clear();
    }

    @Test
    void save() {
        orderRepository.save(new Order(
                OrderNo.of("ORD1234"),
                new Orderer(MemberId.of("member"), "name"),
                List.of(new OrderLine(ProductId.of("PRDID"), new Money(1000), 2)),
                new ShippingInfo(new Address("12345", "addr1", "addr2"), "집앞",
                        new Receiver("아무개", "010-0000-0000")),
                OrderState.PAYMENT_WAITING
        ));
    }

    @Test
    void findById() {
        jdbcTemplate.update(
                """
                        insert into purchase_order
                        values (?,?,?,?,?,?,?,?,?,?,?,?,?)
                        """,
                "ORD1234", 1, "user1", "사용자1", 5000,
                "11122", "주소1", "주소2", "1층",
                "받는사람", "010-1234-5689",
                OrderState.PAYMENT_WAITING.name(), LocalDateTime.now()
        );
        jdbcTemplate.update(
                """
                     insert into order_line 
                     values (?,?,?,?,?,?)
                     """,
                "ORD1234", 0, "PROD1", 1000, 5, 5000
        );
        Optional<Order> ordOpt = orderRepository.findById(OrderNo.of("ORD1234"));
        assertThat(ordOpt).isPresent();
        Order order = ordOpt.get();
        assertThat(order.getOrderLines()).hasSize(1);
    }

    @Test
    void nextOrderNo() {
        OrderNo orderNo = orderRepository.nextOrderNo();
        logger.info("nextOrderNo: {}", orderNo.getNumber());
    }
}