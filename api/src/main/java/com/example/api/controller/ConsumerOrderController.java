package com.example.api.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Order;

@RestController
@RequestMapping("/api/consumer/customers/{customerId}/orders")
public class ConsumerOrderController { 

	// Sample in-memory order storage
	//private AtomicLong orderIdCounter = new AtomicLong(1000);
    private List<Order> orders = new ArrayList<>();
 
    // Endpoint to create a new order for a consumer customer
    @PostMapping
    public ResponseEntity<String> createOrder(@PathVariable int customerId, @RequestBody Order newOrder) {
    	newOrder.setCustomerId(customerId);
        orders.add(newOrder);     
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully");
    }
 

	// Endpoint to update an existing order for a consumer customer
    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable int customerId, @PathVariable int orderId, @RequestBody Order updatedOrder) {
        for (Order order : orders) {
            if (order.getOrderId()==(orderId) && order.getCustomerId()==(customerId)) {
                order.setStatus(updatedOrder.getStatus());
                return ResponseEntity.ok("Order updated successfully");
            }
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint to get all orders for a consumer customer
    @GetMapping
    public List<Order> getAllOrdersForCustomer(@PathVariable Long customerId) {
        return orders.stream()
                .filter(order -> order.getCustomerId()==customerId)
                .collect(Collectors.toList());
    }

    // Endpoint to get details of a specific order for a consumer customer
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable int customerId, @PathVariable int orderId) {
        for (Order order : orders) {
            if (order.getOrderId()==(orderId) && order.getCustomerId()==(customerId)) {
                return ResponseEntity.ok(order);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
