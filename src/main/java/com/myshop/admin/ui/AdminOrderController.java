package com.myshop.admin.ui;

import com.myshop.common.ui.Pagination;
import com.myshop.order.command.application.StartShippingRequest;
import com.myshop.order.command.application.StartShippingService;
import com.myshop.order.query.application.ListRequest;
import com.myshop.order.query.application.OrderDetail;
import com.myshop.order.query.application.OrderDetailService;
import com.myshop.order.query.application.OrderViewListService;
import com.myshop.order.query.dto.OrderSummary;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AdminOrderController {

    private OrderViewListService orderViewListService;
    private OrderDetailService orderDetailService;
    private StartShippingService startShippingService;

    public AdminOrderController(OrderViewListService orderViewListService, OrderDetailService orderDetailService, StartShippingService startShippingService) {
        this.orderViewListService = orderViewListService;
        this.orderDetailService = orderDetailService;
        this.startShippingService = startShippingService;
    }

    @RequestMapping("/admin/orders")
    public String orders(@RequestParam(name = "p", defaultValue = "1") int page,
                         ModelMap modelMap) {
        int size = 20;
        ListRequest listRequest = new ListRequest(page - 1, size);
        Page<OrderSummary> orderPage = orderViewListService.getList(listRequest);
        modelMap.addAttribute("orderPage", orderPage);
        modelMap.addAttribute("pagination",
                new Pagination(
                        orderPage.getNumber() + 1,
                        orderPage.getTotalPages(),
                        5
                ));
        return "admin/adminOrders";
    }

    @RequestMapping("/admin/orders/{orderNo}")
    public String orderDetail(@PathVariable("orderNo") String orderNo, ModelMap modelMap) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetail(orderNo);
        if (orderDetail.isPresent()) {
            modelMap.addAttribute("order", orderDetail.get());
            return "admin/adminOrderDetail";
        } else {
            return "admin/noOrder";
        }
    }

    @RequestMapping(value = "/admin/orders/{orderNo}/shipping", method = RequestMethod.POST)
    public String startShippingOrder(@PathVariable("orderNo") String orderNo,
                                     @RequestParam("version") long version) {
        try {
            startShippingService.startShipping(new StartShippingRequest(orderNo, version));
            return "admin/adminOrderShipped";
        } catch (OptimisticLockingFailureException e) {
            return "admin/adminOrderLockFail";
        }
    }
}
