package com.myshop.order.ui;

import com.myshop.catalog.query.product.ProductData;
import com.myshop.catalog.query.product.ProductQueryService;
import com.myshop.common.ValidationErrorException;
import com.myshop.member.command.domain.MemberId;
import com.myshop.order.command.application.NoOrderProductException;
import com.myshop.order.command.application.OrderProduct;
import com.myshop.order.command.application.OrderRequest;
import com.myshop.order.command.application.PlaceOrderService;
import com.myshop.order.command.domain.OrderNo;
import com.myshop.order.command.domain.OrdererService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {
    private ProductQueryService productQueryService;
    private PlaceOrderService placeOrderService;
    private OrdererService ordererService;

    public OrderController(ProductQueryService productQueryService,
                           PlaceOrderService placeOrderService,
                           OrdererService ordererService) {
        this.productQueryService = productQueryService;
        this.placeOrderService = placeOrderService;
        this.ordererService = ordererService;
    }

    @PostMapping("/orders/orderConfirm")
    public String orderConfirm(@ModelAttribute("orderReq") OrderRequest orderRequest,
                               ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderRequest.setOrdererMemberId(MemberId.of(user.getUsername()));
        populateProductsAndTotalAmountsModel(orderRequest, modelMap);
        return "order/confirm";
    }

    private void populateProductsAndTotalAmountsModel(OrderRequest orderRequest, ModelMap modelMap) {
        List<ProductData> products = getProducts(orderRequest.getOrderProducts());
        modelMap.addAttribute("products", products);
        int totalAmounts = 0;
        for (int i = 0 ; i < orderRequest.getOrderProducts().size() ; i++) {
            OrderProduct op = orderRequest.getOrderProducts().get(i);
            ProductData prod = products.get(i);
            totalAmounts += op.getQuantity() * prod.getPrice().getValue();
        }
        modelMap.addAttribute("totalAmounts", totalAmounts);
    }

    private List<ProductData> getProducts(List<OrderProduct> orderProducts) {
        List<ProductData> results = new ArrayList<>();
        for (OrderProduct op : orderProducts) {
            Optional<ProductData> productOpt = productQueryService.getProduct(op.getProductId());
            ProductData product = productOpt.orElseThrow(() -> new NoOrderProductException(op.getProductId()));
            results.add(product);
        }
        return results;
    }

    @PostMapping("/orders/order")
    public String order(@ModelAttribute("orderReq") OrderRequest orderRequest,
                        BindingResult bindingResult,
                        ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderRequest.setOrdererMemberId(MemberId.of(user.getUsername()));
        try {
            OrderNo orderNo = placeOrderService.placeOrder(orderRequest);
            modelMap.addAttribute("orderNo", orderNo.getNumber());
            return "order/orderComplete";
        } catch (ValidationErrorException e) {
            e.getErrors().forEach(err -> {
                if (err.hasName()) {
                    bindingResult.rejectValue(err.getName(), err.getCode());
                } else {
                    bindingResult.reject(err.getCode());
                }
            });
            populateProductsAndTotalAmountsModel(orderRequest, modelMap);
            return "order/confirm";
        }
    }

    @ExceptionHandler(NoOrderProductException.class)
    public String handleNoOrderProduct() {
        return "order/noProduct";
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

}
