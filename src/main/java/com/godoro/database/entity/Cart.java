package com.godoro.database.entity;

public class Cart {
	private long  cartId;
	private double totalAmount;
	private Consumer consumer;
	
	public Cart(long cartId, double totalAmount) {
		this.cartId = cartId;
		this.totalAmount = totalAmount;
	}
	
	public Cart(){
		
	}
	
	public void increaseTotalAmount(double amount) {
		setTotalAmount(getTotalAmount()+amount);
	}
	public void decreaseTotalAmount(double amount) {
		setTotalAmount(getTotalAmount()-amount);
	}
	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	
	
	
}
