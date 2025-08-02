package com.agam.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequeest(
        @NotNull(message = "Product is mandatory")
        Integer productId,
        @NotNull(message = "Quantity is mandatory")
        Double quantity
) {
}
