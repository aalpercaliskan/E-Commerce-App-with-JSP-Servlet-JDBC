package com.godoro.database.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.godoro.database.entity.Cart;
import com.godoro.database.entity.Consumer;

public class CartManager extends BaseManager<Cart>{

	@Override
	protected Cart parse(ResultSet resultSet) throws Exception {
		long cartId = resultSet.getLong("cartId");
		double totalAmount = resultSet.getDouble("totalAmount"); 
		long consumerId = resultSet.getLong("consumerId");
		Cart cart = new Cart(cartId, totalAmount);
		Consumer consumer = getConsumer(consumerId);
		cart.setConsumer(consumer);
		return cart;
	}
	
	private Consumer getConsumer(long consumerId) throws Exception {
		Consumer consumer = null;
		ConsumerManager consumerManager = new ConsumerManager();
		consumer = consumerManager.find(consumerId);
		return consumer;
	}
	
	@Override
	protected String findSQL() {
		return "select * from Cart where cartId = ?";
	}

	@Override
	protected String listSQL() {
		return "select * from Cart";
	}

	@Override
	protected String updateSQL() {
		return "update Cart set totalAmount = ?, consumerId = ? where cartId = ?";
	}

	@Override
	protected String deleteSQL() {
		return "delete from Cart where cartId = ?";
	}

	@Override
	protected String insertSQL() {
		return "insert into Cart(totalAmount,consumerId) values(?,?)";
	}

	@Override
	protected PreparedStatement setUpdateStatement(PreparedStatement statement, Cart cart) throws Exception {
		setInsertStatement(statement, cart);
		statement.setLong(3, cart.getCartId());
		return statement;
	}

	@Override
	protected PreparedStatement setInsertStatement(PreparedStatement statement, Cart cart) throws Exception {
		statement.setDouble(1, cart.getTotalAmount());
		statement.setLong(2, cart.getConsumer().getConsumerId());
		return statement;
	}
	
	public Cart findByConsumer(long consumerId) throws Exception {
		Cart cart = null;
		
		connect();
		
		String sql = "select * from Cart where consumerId = ?";
		PreparedStatement statement =connection.prepareStatement(sql);
		statement.setLong(1, consumerId);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			cart = parse(resultSet);
		}
		disconnect();
		return cart;
	}


}
