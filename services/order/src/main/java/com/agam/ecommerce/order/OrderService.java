package com.agam.ecommerce.order;

import com.agam.ecommerce.customer.CustomerClient;
import com.agam.ecommerce.exception.BusinessException;
import com.agam.ecommerce.kafka.OrderConfirmation;
import com.agam.ecommerce.kafka.OrderProducer;
import com.agam.ecommerce.orderline.OrderLineRequest;
import com.agam.ecommerce.orderline.OrderLineService;
import com.agam.ecommerce.payment.PaymentClient;
import com.agam.ecommerce.payment.PaymentRequest;
import com.agam.ecommerce.product.ProductClient;
import com.agam.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    public  final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {

        // check for the customer --> openFeign
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot Create Order :: " +
                        "No Customer Exist with provided Id:  "+orderRequest.customerId()));


        // purchase the products --> product microservice (rest template)
        var purchasedProducts = this.productClient.purchaseProducts(orderRequest.products());
        //persist order
        var order = orderRepository.save(orderMapper.toOrder(orderRequest));
        // persist order lines
        for(PurchaseRequest purchaseRequest : orderRequest.products()){
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    orderRequest.id(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ));
        }

        // todo start payment process

        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                orderRequest.id(),
                orderRequest.reference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order notification --> notification microservice (Kafka)
            orderProducer.sendOrderConfirmation(
                    new OrderConfirmation(
                            orderRequest.reference(),
                            orderRequest.amount(),
                            orderRequest.paymentMethod(),
                            customer,
                            purchasedProducts
                    )
            );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream().map(orderMapper :: fromOrder).collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("No Order found with Order Id:: "+orderId));
    }
}
