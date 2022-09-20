package com.godoro.database.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.godoro.database.entity.Category;

public class CategoryManager extends BaseManager<Category>{
	
	protected Category parse(ResultSet resultSet) throws Exception {
		long categoryId = resultSet.getLong("categoryId");
		String categoryName = resultSet.getString("categoryName"); 
		Category category = new Category(categoryId, categoryName);
		return category;
	}
	
	@Override
	protected String findSQL() {
		return  "select * from Category where categoryId = ?";
	}

	@Override
	protected String listSQL() {
		return "select * from Category";
	}

	@Override
	protected String updateSQL() {
		return "update Category set categoryName = ? where categoryId = ?";
	}

	@Override
	protected String deleteSQL() {
		return "delete from Category where categoryId = ?";
	}

	@Override
	protected String insertSQL() {
		return "insert into Category(categoryName) values(?)";
	}

	@Override
	protected PreparedStatement setUpdateStatement(PreparedStatement statement, Category category) throws Exception {
		setInsertStatement(statement, category);
		statement.setLong(2, category.getCategoryId());
		return statement;
	}

	@Override
	protected PreparedStatement setInsertStatement(PreparedStatement statement, Category category)throws Exception  {
		statement.setString(1, category.getCategoryName());
		return statement;
	}
	
	public Category find(String categoryName) throws Exception {
		Category category = null;
		
		connect();
		
		String sql = "select * from Category where categoryName = ?";
		PreparedStatement statement =connection.prepareStatement(sql);
		statement.setString(1, categoryName);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			category = parse(resultSet);
		}
		disconnect();
		return category;
	}
}
