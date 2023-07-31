package com.example.api.model;

public class EOrder {
	private int orderId;
    private String customerId;
    private String productName;
    private double amount;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public EOrder(int orderId, String customerId, String productName, double amount) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.productName = productName;
		this.amount = amount;
	}
}