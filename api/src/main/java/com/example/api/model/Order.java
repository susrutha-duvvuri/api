package com.example.api.model;

public class Order {
    private int orderId;
    private int customerId;
    private String status;
	public int getOrderId() {
		return orderId; 
	} 
	
	// Add constructors, getters, setters, and any other required methods.
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Order(int orderId, int customerId,  String status) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.status = status;
	}
	
	
    
    
    
}
