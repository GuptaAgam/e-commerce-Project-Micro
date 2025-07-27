package com.agam.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "FirstName is Required")
        String firstName,
        @NotNull(message = "LastName is Required")
        String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "Customer Email is not correctly formatted")
        String email
) {
}
