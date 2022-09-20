package com.godoro.database.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.godoro.database.entity.Category;
import com.godoro.database.entity.Product;

public class ProductManager extends BaseManager<Product>{
	
	protected Product parse(ResultSet resultSet) throws Exception {
		long productId = resultSet.getLong("productId");
		String productName = resultSet.getString("productName"); 
		double salesPrice = resultSet.getDouble("salesPrice");
		long categoryId = resultSet.getLong("categoryId");
		Product product = new Product(productId, productName, salesPrice);

		Category category = getCategory(categoryId);
		product.setCategory(category);		
		return product;
	}
	
	private Category getCategory(long categoryId) throws Exception {
		Category category = null;
		CategoryManager categoryManager = new CategoryManager();
		category = categoryManager.find(categoryId);
		return category;
	}
	
	@Override
	protected String findSQL() {
		return "select * from Product where productId = ?";
	}

	@Override
	protected String listSQL() {
		return  "select * from Product";
	}

	@Override
	protected String updateSQL() {
		return "update Product set productName = ?, salesPrice = ? , categoryId = ? where productId = ?";
	}

	@Override
	protected String deleteSQL() {
		return "delete from Product where productId = ?";
	}

	@Override
	protected String insertSQL() {
		return "insert into Product(productName,salesPrice,categoryId) values(?,?,?)";
	}

	@Override
	protected PreparedStatement setUpdateStatement(PreparedStatement statement, Product product) throws Exception {
		setInsertStatement(statement, product);
		statement.setLong(4, product.getProductId());
		return statement;
	}

	@Override
	protected PreparedStatement setInsertStatement(PreparedStatement statement, Product product) throws Exception {
		statement.setString(1, product.getProductName());
		statement.setDouble(2, product.getSalesPrice());
		statement.setLong(3, product.getCategory().getCategoryId());
		return statement;
	}
	
	public List<Product> list(long categoryId) throws Exception{
		connect();
		
		String sql = "select * from Product where categoryId = ?";
		PreparedStatement statement =connection.prepareStatement(sql);
		statement.setLong(1, categoryId);
		ResultSet resultSet = statement.executeQuery();
		List<Product> productList = parseList(resultSet);
		
		disconnect();
		
		
		return productList;
	}
	
}
