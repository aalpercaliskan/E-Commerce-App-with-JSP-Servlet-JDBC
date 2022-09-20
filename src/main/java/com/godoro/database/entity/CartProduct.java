package com.godoro.database.entity;

public class CartProduct {
	private long cartProductId;
	private int salesQuantity;
	private double salesPrice;
	private Product product;
	private Cart cart;
	
	
	public CartProduct(long cartProductId, int salesQuantity, double salesPrice) {
		this.cartProductId = cartProductId;
		this.salesQuantity = salesQuantity;
		this.salesPrice = salesPrice;
	}
	
	public CartProduct() {
		
	}
	
	public double getCartProductTotalAmount() {
		return getSalesPrice()*getSalesQuantity();
	}
	public long getCartProductId() {
		return cartProductId;
	}

	public void setCartProductId(long cartProductId) {
		this.cartProductId = cartProductId;
	}

	public int getSalesQuantity() {
		return salesQuantity;
	}

	public void setSalesQuantity(int salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	public double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
