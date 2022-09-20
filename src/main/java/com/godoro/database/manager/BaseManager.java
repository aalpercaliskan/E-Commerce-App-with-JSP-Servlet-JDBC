package com.godoro.database.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

abstract public class BaseManager<E> {
	private String url = "jdbc:postgresql://localhost/shoppingdb";
	private String user = "postgres";
	private String password ="alper4545C";
	private String driver = "org.postgresql.Driver";
	
	protected Connection connection;
	
	public BaseManager() {
		try {
			Class.forName(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void connect() throws Exception {
		connection = DriverManager.getConnection(url,user,password);
	}
	
	protected void disconnect() throws Exception {
		if(connection != null) {
			connection.close();
		}
	}
	
	protected List<E> parseList(ResultSet resultSet) throws Exception{
		List<E> entityList = new ArrayList<>();
		while(resultSet.next()) {
			E entity = parse(resultSet);
			entityList.add(entity);
		}
		return entityList;
	}
	
	public E find(long entityId) throws Exception {
		E entity = null;
		
		connect();
		
		String sql = findSQL();
		PreparedStatement statement =connection.prepareStatement(sql);
		statement.setLong(1, entityId);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			entity = parse(resultSet);
		}
		disconnect();
		return entity;
	}
	
	
	public List<E> list() throws Exception{
		connect();
		
		String sql = listSQL();
		PreparedStatement statement =connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		List<E> entityList = parseList(resultSet);
		
		disconnect();
		return entityList;
	}
	
	public boolean delete(long entityId) throws Exception {
		connect();
		
		String sql = deleteSQL();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, entityId);
		int affected = statement.executeUpdate();
		
		connection.close();
		return affected>0;
	}
	
	public boolean update(E entity) throws Exception {
		connect();
		
		String sql = updateSQL();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement = setUpdateStatement(statement, entity);
		int affected = statement.executeUpdate();
		
		connection.close();
		
		System.out.println("Etkilenmiş " + affected);
		return affected>0;
	}
	
	public boolean insert(E entity) throws Exception{
		connect();
		
		String sql = insertSQL();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement = setInsertStatement(statement, entity);
		int affected = statement.executeUpdate();
		
		connection.close();
		
		System.out.println("Etkilenmiş " + affected);
		return affected>0;
	}
	
	protected abstract E parse(ResultSet resultSet) throws Exception;
	protected abstract String findSQL();
	protected abstract String listSQL();
	protected abstract String updateSQL();
	protected abstract String deleteSQL();
	protected abstract String insertSQL();
	protected abstract PreparedStatement setUpdateStatement(PreparedStatement statement, E entity) throws Exception;
	protected abstract PreparedStatement setInsertStatement(PreparedStatement statement, E entity) throws Exception;
	
}
