package com.example.api.model;


public class Customer {

	private String customerId;
	private String companyName;
	
	// Other customer properties, getters, setters, and constructors
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	public Customer(String customerId, String companyName, String contactName) {
		super();
		this.customerId = customerId;
		this.companyName = companyName;
	}

	


}
