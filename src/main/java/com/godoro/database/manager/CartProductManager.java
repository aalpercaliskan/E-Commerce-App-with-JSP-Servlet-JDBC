package com.godoro.database.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.godoro.database.entity.Cart;
import com.godoro.database.entity.CartProduct;
import com.godoro.database.entity.Product;

public class CartProductManager extends BaseManager<CartProduct>{
	
	protected CartProduct parse(ResultSet resultSet) throws Exception {
		long cartProductId = resultSet.getLong("cartProductId");
		int salesQuantity = resultSet.getInt("salesQuantity"); 
		double salesPrice = resultSet.getDouble("salesPrice");
		long productId = resultSet.getLong("productId");
		long cartId = resultSet.getLong("cartId");

		CartProduct cartProduct = new CartProduct(cartProductId, salesQuantity, salesPrice);
		Product product = getProduct(productId);
		Cart cart = getCart(cartId);
		cartProduct.setProduct(product);
		cartProduct.setCart(cart);
		return cartProduct;
	}
	
	private Product getProduct(long productId) throws Exception {
		Product product = null;
		ProductManager productManager = new ProductManager();
		product = productManager.find(productId);
		return product;
	}
	
	private Cart getCart(long cartId) throws Exception {
		Cart cart = null;
		CartManager cartManager = new CartManager();
		cart = cartManager.find(cartId);
		return cart;
	}

	@Override
	protected String findSQL() {
		return "select * from CartProduct where cartProductId = ?";
	}

	@Override
	protected String listSQL() {
		return  "select * from CartProduct";
	}

	@Override
	protected String updateSQL() {
		return "update CartProduct set salesQuantity = ?, salesPrice = ? , productId = ?, cartId = ? where cartProductId = ?";
	}

	@Override
	protected String deleteSQL() {
		return "delete from CartProduct where cartProductId = ?";
	}

	@Override
	protected String insertSQL() {
		return "insert into CartProduct(salesQuantity,salesPrice,productId,cartId) values(?,?,?,?)";
	}

	@Override
	protected PreparedStatement setUpdateStatement(PreparedStatement statement, CartProduct cartProduct) throws Exception {
		setInsertStatement(statement, cartProduct);
		statement.setLong(5, cartProduct.getCartProductId());
		return statement;
	}

	@Override
	protected PreparedStatement setInsertStatement(PreparedStatement statement, CartProduct cartProduct) throws Exception {
		statement.setInt(1, cartProduct.getSalesQuantity());
		statement.setDouble(2, cartProduct.getSalesPrice());
		statement.setLong(3, cartProduct.getProduct().getProductId());
		statement.setLong(4, cartProduct.getCart().getCartId());
		return statement;
	}
	
	public List<CartProduct> list(long cartId) throws Exception{
		connect();
		
		String sql = "select * from CartProduct where cartId = ?";
		PreparedStatement statement =connection.prepareStatement(sql);
		statement.setLong(1, cartId);
		ResultSet resultSet = statement.executeQuery();
		List<CartProduct> cartProductList = parseList(resultSet);
		
		disconnect();
		return cartProductList;
	}
	
	public CartProduct find(long productId, long cartId) throws Exception {
		CartProduct cartProduct = null;
		
		connect();
		
		String sql =  "select * from CartProduct where productId = ? and cartId = ?";
		PreparedStatement statement =connection.prepareStatement(sql);
		statement.setLong(1, productId);
		statement.setLong(2, cartId);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			cartProduct = parse(resultSet);
		}
		disconnect();
		return cartProduct;
	}
	
	public boolean deleteAtCart(long cartId) throws Exception {
		connect();
		
		String sql = "delete from CartProduct where cartId = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, cartId);
		int affected = statement.executeUpdate();
		
		connection.close();
		return affected>0;
	}
}
