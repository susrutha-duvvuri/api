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
import com.example.api.model.EOrder;


@RestController
@RequestMapping("/api/enterprise/customers/{customerId}/orders")
public class OrdersController {
	
	private List<EOrder> orders = new ArrayList<>();
	
	
	@PostMapping
	public ResponseEntity<EOrder> createOrder(@PathVariable String customerId, @RequestBody EOrder newOrder) {
		newOrder.setCustomerId(customerId);
        orders.add(newOrder); // Logic to create a new order for the specified enterprise customer
		return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
	}


	@PutMapping("/{orderId}")
	public ResponseEntity<EOrder> updateOrder(@PathVariable String customerId, @PathVariable int orderId,
			@RequestBody EOrder updatedOrder) {
		// Logic to update an existing order for the specified enterprise customer
		EOrder existingOrder = findOrderByCustomerIdAndOrderId(customerId, orderId);
		if (existingOrder != null) {
			existingOrder.setProductName(updatedOrder.getProductName());
			existingOrder.setAmount(updatedOrder.getAmount());
			// Update other order properties as needed
			return ResponseEntity.ok(existingOrder);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<EOrder>> getOrdersByCustomerId(@PathVariable String customerId) {
		// Logic to fetch all orders for the specified enterprise customer
		List<EOrder> customerOrders = findOrdersByCustomerId(customerId);
		return ResponseEntity.ok(customerOrders);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<EOrder> getOrderById(@PathVariable String customerId, @PathVariable int orderId) {
		// Logic to fetch an order by customerId and orderId
		EOrder order = findOrderByCustomerIdAndOrderId(customerId, orderId);
		if (order != null) {
			return ResponseEntity.ok(order);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Helper method to find orders by customerId
	private List<EOrder> findOrdersByCustomerId(String customerId) {
		return orders.stream().filter(order -> order.getCustomerId().equals(customerId)).collect(Collectors.toList());
	}

	// Helper method to find an order by customerId and orderId
	private EOrder findOrderByCustomerIdAndOrderId(String customerId, int orderId) {
		return orders.stream()
				.filter(order -> order.getCustomerId().equals(customerId) && order.getOrderId() == (orderId))
				.findFirst().orElse(null);
	}
}
