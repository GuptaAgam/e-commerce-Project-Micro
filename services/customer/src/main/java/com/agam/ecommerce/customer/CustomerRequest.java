package com.agam.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

        String id,
        @NotNull(message = "Customer first name cannot be null")
        String firstname,
        @NotNull(message = "Customer Last name cannot be null")
        String lastname,
        @NotNull(message = "Customer email cannot be null")
        @Email(message = "Email not valid")
        String email,

        Address address
) {


}
