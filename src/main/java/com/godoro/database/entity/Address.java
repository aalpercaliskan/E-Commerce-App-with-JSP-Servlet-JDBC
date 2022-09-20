package com.godoro.database.entity;

public class Address {
	private long addressId;
	private String provinceName;
	private String addressLine1;
	private String addressLine2;
	private Consumer consumer;
	
	public Address(long addressId, String provinceName, String addressLine1, String addressLine2) {
		this.addressId = addressId;
		this.provinceName = provinceName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
	}
	
	public Address() {
		
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}


	
	
	
	
}
