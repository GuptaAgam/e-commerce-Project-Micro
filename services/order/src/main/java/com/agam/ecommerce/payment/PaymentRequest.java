package com.agam.ecommerce.payment;

import com.agam.ecommerce.customer.CustomerResponse;
import com.agam.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
