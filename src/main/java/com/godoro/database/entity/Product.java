package com.godoro.database.entity;

public class Product {
	private long productId;
	private String productName;
	private double salesPrice;
	private Category category;
	
	public Product(long productId, String productName, double salesPrice) {
		this.productId = productId;
		this.productName = productName;
		this.salesPrice = salesPrice;
	}
	public Product() {
		
	}	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
