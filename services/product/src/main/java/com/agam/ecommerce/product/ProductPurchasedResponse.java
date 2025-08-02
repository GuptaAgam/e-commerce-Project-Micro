package com.agam.ecommerce.product;

import java.math.BigDecimal;

public record ProductPurchasedResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity

) {
}
