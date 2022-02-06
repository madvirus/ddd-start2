package com.myshop.order.query.application;

import com.myshop.order.query.dao.OrderSummaryDao;
import com.myshop.order.query.dto.OrderSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderViewListService {
    private OrderSummaryDao orderSummaryDao;

    public OrderViewListService(OrderSummaryDao orderSummaryDao) {
        this.orderSummaryDao = orderSummaryDao;
    }

    @Transactional
    public Page<OrderSummary> getList(ListRequest listReq) {
        PageRequest pageable = PageRequest.of(
                listReq.getPage(),
                listReq.getSize(),
                Sort.by(Sort.Direction.DESC, "number"));
        return orderSummaryDao.findAll(pageable);
    }
}
