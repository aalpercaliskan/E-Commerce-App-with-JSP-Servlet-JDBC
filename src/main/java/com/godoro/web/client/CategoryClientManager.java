package com.godoro.web.client;

import java.io.InputStream;
import java.util.List;

import org.w3c.dom.Document;

import com.godoro.core.utils.WebHelper;
import com.godoro.core.utils.XmlHelper;
import com.godoro.database.entity.Category;
import com.godoro.xml.CategoryXmlManager;

public class CategoryClientManager extends BaseClientManager<Category> {

	private final static CategoryXmlManager CATEGORY_XML_MANAGER = new CategoryXmlManager();
	private final static String FIND_ADDRESS = "http://localhost:8080/Website/api/findCategory?id=%d";
	private final static String LİST_ADDRESS = "http://localhost:8080/Website/api/categories";
	private final static String INSERT_ADDRESS = "http://localhost:8080/Website/admin/addCategory" ;
	private final static String UPDATE_ADDRESS = "http://localhost:8080/Website/admin/updateCategory" ;
	private final static String DELETE_ADDRESS = "http://localhost:8080/Website/admin/deleteCategory?id=%d";
	
	protected String getFindAddress(long categoryId) {
		return String.format(FIND_ADDRESS,categoryId);
	}

	protected String getListAddress() {
		return LİST_ADDRESS;
	}

	protected String getInsertAddress() {
		return INSERT_ADDRESS;
	}

	protected String getUpdateAddress() {
		return UPDATE_ADDRESS;
	}

	protected String getDeleteAddress(long categoryId) {
		return String.format(DELETE_ADDRESS,categoryId);
	}
	
	@Override
	protected Document getDocument(Category category) throws Exception{
		Document document = null;
		document = CATEGORY_XML_MANAGER.format(category ,"category");
		return document;
	}

	@Override
	protected Category getEntity(Document document) {
		Category category = null;
		category = CATEGORY_XML_MANAGER.parse(document);
		return category;
	}

	@Override
	protected List<Category> getEntityList(Document document) {
		List<Category> categoryList = null;
		categoryList =  CATEGORY_XML_MANAGER.parseList(document, "category");
		return categoryList;
	}
	
	public Category find(String categoryName) throws Exception {
		Category category = null;
		String address = String.format("http://localhost:8080/Website/api/findCategoryByName?name=%d",categoryName);
		InputStream in = WebHelper.get(address);
		Document document = XmlHelper.parse(in);
		category = CATEGORY_XML_MANAGER.parse(document);
		return category;
	}
}
