package com.myshop.order.command.application;

import com.myshop.common.ValidationError;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderRequestValidator {
    public List<ValidationError> validate(OrderRequest orderRequest) {
        List<ValidationError> errors = new ArrayList<>();
        if (orderRequest == null) {
            errors.add(ValidationError.of("required"));
        } else {
            if (orderRequest.getOrdererMemberId() == null)
                errors.add(ValidationError.of("ordererMemberId", "required"));
            if (orderRequest.getOrderProducts() == null)
                errors.add(ValidationError.of("orderProducts", "required"));
            if (orderRequest.getOrderProducts().isEmpty())
                errors.add(ValidationError.of("orderProducts", "required"));

            if (orderRequest.getShippingInfo() == null) {
                errors.add(ValidationError.of("shippingInfo", "required"));
            } else {
                if (orderRequest.getShippingInfo().getReceiver() == null) {
                    errors.add(ValidationError.of("shippingInfo.receiver", "required"));
                } else {
                    if (!StringUtils.hasText(orderRequest.getShippingInfo().getReceiver().getName())) {
                        errors.add(ValidationError.of("shippingInfo.receiver.name", "required"));
                    }
                    if (!StringUtils.hasText(orderRequest.getShippingInfo().getReceiver().getPhone())) {
                        errors.add(ValidationError.of("shippingInfo.receiver.phone", "required"));
                    }
                    if (orderRequest.getShippingInfo().getAddress() == null) {
                        errors.add(ValidationError.of("shippingInfo.address", "required"));
                    } else {
                        if (!StringUtils.hasText(orderRequest.getShippingInfo().getAddress().getZipCode())) {
                            errors.add(ValidationError.of("shippingInfo.address.zipCode", "required"));
                        }
                        if (!StringUtils.hasText(orderRequest.getShippingInfo().getAddress().getAddress1())) {
                            errors.add(ValidationError.of("shippingInfo.address.address1", "required"));
                        }
                        if (!StringUtils.hasText(orderRequest.getShippingInfo().getAddress().getAddress2())) {
                            errors.add(ValidationError.of("shippingInfo.address.address2", "required"));
                        }
                    }
                }
            }
        }
        return errors;
    }
}
