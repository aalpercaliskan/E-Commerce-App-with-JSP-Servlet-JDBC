package com.godoro.database.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.godoro.database.entity.Address;
import com.godoro.database.entity.Consumer;

public class AddressManager extends BaseManager<Address>{

	@Override
	protected Address parse(ResultSet resultSet) throws Exception {
		long addressId = resultSet.getLong("addressId");
		String provinceName = resultSet.getString("provinceName"); 
		String addressLine1 = resultSet.getString("addressLine1");
		String addressLine2 = resultSet.getString("addressLine2");
		long consumerId = resultSet.getLong("consumerId");
		
		Address address = new Address(addressId, provinceName, addressLine1, addressLine2);
		Consumer consumer = getConsumer(consumerId);
		address.setConsumer(consumer);
		return address;
	}
	
	private Consumer getConsumer(long consumerId) throws Exception {
		Consumer consumer = null;
		ConsumerManager consumerManager = new ConsumerManager();
		consumer = consumerManager.find(consumerId);
		return consumer;
	}
	
	@Override
	protected String findSQL() {
		return "select * from Address where addressId = ?";
	}

	@Override
	protected String listSQL() {
		return "select * from Address";
	}

	@Override
	protected String updateSQL() {
		return "update Address set provinceName = ?, addressLine1 = ? , addressLine2 = ? , consumerId = ? where addressId = ?";
	}

	@Override
	protected String deleteSQL() {
		return "delete from Address where addressId = ?";
	}

	@Override
	protected String insertSQL() {
		return "insert into Address(provinceName, addressLine1, addressLine2, consumerId) values(?,?,?,?)";
	}

	@Override
	protected PreparedStatement setUpdateStatement(PreparedStatement statement, Address address) throws Exception {
		setInsertStatement(statement, address);
		statement.setLong(5, address.getAddressId());
		return statement;
	}

	@Override
	protected PreparedStatement setInsertStatement(PreparedStatement statement, Address address) throws Exception {
		statement.setString(1, address.getProvinceName());
		statement.setString(2, address.getAddressLine1());
		statement.setString(3, address.getAddressLine2());
		statement.setLong(4, address.getConsumer().getConsumerId());
		return statement;
	}
	
	public Address findByConsumer(long consumerId) throws Exception {
		Address address = null;
		
		connect();
		
		String sql = "select * from Address where consumerId = ?";
		PreparedStatement statement =connection.prepareStatement(sql);
		statement.setLong(1, consumerId);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			address = parse(resultSet);
		}
		disconnect();
		return address;
	}

}
