package com.example.api.model;


public class Service {

	private String connectionId;
	private String customerId;
	private String serviceType;
	private boolean active;
	// Other service properties, getters, setters, and constructors
	public String getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}
	public String getCustomerId() {
		return customerId; 
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Service(String connectionId, String customerId, String serviceType, boolean active) {
		super();
		this.connectionId = connectionId;
		this.customerId = customerId;
		this.serviceType = serviceType;
		this.active = active;
	}
	

	
}