package com.agam.ecommerce.kafka;

import com.agam.ecommerce.customer.CustomerResponse;
import com.agam.ecommerce.order.PaymentMethod;
import com.agam.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
