package com.example.api.controller;


import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.api.model.Customer;


@RestController
@RequestMapping("/api/enterprise/customers")
public class EnterpriseCustomerController {

	private List<Customer> customers = new ArrayList<>();

	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		// Logic to add a new enterprise customer
		customers.add(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId,
			@RequestBody Customer updatedCustomer) {
		// Logic to update an existing enterprise customer
		Customer existingCustomer = findCustomerById(customerId);
		if (existingCustomer != null) {
			existingCustomer.setCompanyName(updatedCustomer.getCompanyName());
			// Update other customer properties as needed
			return ResponseEntity.ok(existingCustomer);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		// Logic to fetch all enterprise customers
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
		// Logic to fetch an enterprise customer by customerId
		Customer customer = findCustomerById(customerId);
		if (customer != null) {
			return ResponseEntity.ok(customer);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Helper method to find a customer by customerId
	private Customer findCustomerById(String customerId) {
		return customers.stream().filter(customer -> customer.getCustomerId().equals(customerId)).findFirst()
				.orElse(null);
	}
}