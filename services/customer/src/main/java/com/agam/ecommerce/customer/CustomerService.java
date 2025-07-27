package com.agam.ecommerce.customer;

import ch.qos.logback.core.util.StringUtil;
import com.agam.ecommerce.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request){
        var customer = customerMapper.toCustomer(request);
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.id()).orElseThrow(() -> new CustomerNotFoundException(String.format("Cannot update customer no customer found with id:: %s", customerRequest.id())));
        mergeCustomer(customer, customerRequest);
    }

    private void mergeCustomer(Customer customer, @Valid CustomerRequest customerRequest) {
        if(StringUtil.isNullOrEmpty(customerRequest.firstname())){
            customer.setFirstname(customerRequest.firstname());
        }
        if(StringUtil.isNullOrEmpty(customerRequest.lastname())){
            customer.setLastname(customerRequest.lastname());
        }
        if(StringUtil.isNullOrEmpty(customerRequest.email())){
            customer.setEmail(customerRequest.email());
        }
        if(customerRequest.address() != null){
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::fromCustomer).collect(Collectors.toList());
    }

    public Boolean existById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId).map(customerMapper::fromCustomer).orElseThrow(() -> new CustomerNotFoundException(String.format("No customer is found with this id: %s", customerId)));
    }

    public void deleteCustomer(String customerId) {
         customerRepository.deleteById(customerId);
    }
}
