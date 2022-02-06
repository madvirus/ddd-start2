package com.myshop.order.query.dao;

import com.myshop.order.query.dto.OrderSummary;
import com.myshop.order.query.dto.OrderView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql("classpath:shop-init-test.sql")
class OrderSummaryDaoIT {
    @Autowired
    private OrderSummaryDao orderSummaryDao;

    @Test
    void findAllSpec() {
        LocalDateTime from = LocalDateTime.of(2022, 1, 1, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2022, 1, 2, 0, 0, 0);

        Specification<OrderSummary> spec = OrderSummarySpecs.ordererId("user1")
                .and(OrderSummarySpecs.orderDateBetween(from, to));

        List<OrderSummary> results = orderSummaryDao.findAll(spec);
        assertThat(results).hasSize(1);
    }

    @Test
    void findAllSpecSort() {
        LocalDateTime from = LocalDateTime.of(2022, 1, 1, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2022, 1, 2, 0, 0, 0);

        Specification<OrderSummary> spec = OrderSummarySpecs.ordererId("user1")
                .and(OrderSummarySpecs.orderDateBetween(from, to));

        Sort sort = Sort.by("number").ascending().and(Sort.by("orderDate").descending());

        List<OrderSummary> results = orderSummaryDao.findAll(spec, sort);
        assertThat(results).hasSize(1);
    }

    @Test
    void findAllSpecPageable() {
        LocalDateTime from = LocalDateTime.of(2022, 1, 1, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2023, 1, 2, 0, 0, 0);

        Specification<OrderSummary> spec = OrderSummarySpecs.orderDateBetween(from, to);

        Pageable pageable = PageRequest.of(1, 1);
        List<OrderSummary> results = orderSummaryDao.findAll(spec, pageable);
        assertThat(results).hasSize(2);
    }

    @Test
    void findByOrdererIdOrderByNumberDesc() {
        List<OrderSummary> results = orderSummaryDao.findByOrdererIdOrderByNumberDesc("user1");
        assertThat(results.get(0).getNumber()).isEqualTo("ORDER-002");
        assertThat(results.get(1).getNumber()).isEqualTo("ORDER-001");
    }

    @Test
    void findByOrdererIdSort() {
        Sort sort = Sort.by("number").ascending();
        List<OrderSummary> results = orderSummaryDao.findByOrdererId("user1", sort);
        assertThat(results.get(0).getNumber()).isEqualTo("ORDER-001");
        assertThat(results.get(1).getNumber()).isEqualTo("ORDER-002");
    }

    @Test
    void findByOrdererIdPageable() {
        Pageable pageable = PageRequest.of(1, 1);
        List<OrderSummary> results = orderSummaryDao.findByOrdererId("user1", pageable);
        assertThat(results.get(0).getNumber()).isEqualTo("ORDER-001");
        assertThat(results.get(1).getNumber()).isEqualTo("ORDER-002");
    }

    @Test
    void findOrderView() {
        List<OrderView> result = orderSummaryDao.findOrderView("user1");
    }
}