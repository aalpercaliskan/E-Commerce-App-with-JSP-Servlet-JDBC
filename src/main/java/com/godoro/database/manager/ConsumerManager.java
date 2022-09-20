package com.godoro.database.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.godoro.database.entity.Consumer;

public class ConsumerManager extends BaseManager<Consumer>{

	@Override
	protected Consumer parse(ResultSet resultSet) throws Exception {
		long consumerId = resultSet.getLong("consumerId");
		String consumerName = resultSet.getString("consumerName"); 
		String consumerPassword = resultSet.getString("consumerPassword");
		Consumer consumer = new Consumer(consumerId, consumerName, consumerPassword);
		return consumer;
	}

	@Override
	protected String findSQL() {
		return "select * from Consumer where consumerId = ?";
	}

	@Override
	protected String listSQL() {
		return "select * from Consumer";
	}

	@Override
	protected String updateSQL() {
		return "update Consumer set consumerName = ?, consumerPassword = ? where consumerId = ?";
	}

	@Override
	protected String deleteSQL() {
		return "delete from Consumer where consumerId = ?";
	}

	@Override
	protected String insertSQL() {
		return "insert into Consumer(consumerName,consumerPassword) values(?,?)";
	}

	@Override
	protected PreparedStatement setUpdateStatement(PreparedStatement statement, Consumer consumer) throws Exception {
		setInsertStatement(statement, consumer);
		statement.setLong(3, consumer.getConsumerId());
		
		return statement;
	}

	@Override
	protected PreparedStatement setInsertStatement(PreparedStatement statement, Consumer consumer) throws Exception {
		statement.setString(1, consumer.getConsumerName());
		statement.setString(2, consumer.getConsumerPassword());
		return statement;
	}
	
	public Consumer find(String consumerName) throws Exception {
		Consumer consumer = null;
		
		connect();
		
		String sql = "select * from Consumer where consumerName = ?";
		PreparedStatement statement =connection.prepareStatement(sql);
		statement.setString(1, consumerName);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			consumer = parse(resultSet);
		}
		disconnect();
		return consumer;
	}

}
