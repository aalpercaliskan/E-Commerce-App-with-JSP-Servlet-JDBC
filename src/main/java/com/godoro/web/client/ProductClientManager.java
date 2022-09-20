package com.godoro.web.client;

import java.io.InputStream;
import java.util.List;

import org.w3c.dom.Document;

import com.godoro.core.utils.WebHelper;
import com.godoro.core.utils.XmlHelper;
import com.godoro.database.entity.Product;
import com.godoro.xml.ProductXmlManager;

public class ProductClientManager extends BaseClientManager<Product>{
	private final static ProductXmlManager PRODUCT_XML_MANAGER = new ProductXmlManager();
	private final static String FIND_ADDRESS = "http://localhost:8080/Website/api/findProduct?id=%d";
	private final static String LİST_ADDRESS = "http://localhost:8080/Website/api/allProducts";
	private final static String INSERT_ADDRESS = "http://localhost:8080/Website/admin/addProduct" ;
	private final static String UPDATE_ADDRESS = "http://localhost:8080/Website/admin/updateProduct" ;
	private final static String DELETE_ADDRESS = "http://localhost:8080/Website/admin/deleteProduct?id=%d";
	
	protected String getFindAddress(long productId) {
		return String.format(FIND_ADDRESS,productId);
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

	protected String getDeleteAddress(long productId) {
		return String.format(DELETE_ADDRESS,productId);
	}

	@Override
	protected Document getDocument(Product product) throws Exception {
		Document document = null;
		document =  PRODUCT_XML_MANAGER.format(product ,"product");
		return document;
	}

	@Override
	protected Product getEntity(Document document) {
		Product product = null;
		product = PRODUCT_XML_MANAGER.parse(document);
		return product;
	}

	@Override
	protected List<Product> getEntityList(Document document) {
		List<Product> productList = null;
		productList = PRODUCT_XML_MANAGER.parseList(document, "product");
		return productList;
	}
	
	public List<Product> list(long categoryId) throws Exception{
		List<Product> productList = null;
		String address = String.format("http://localhost:8080/Website/api/products?id=%d",categoryId);
		InputStream in = WebHelper.get(address);
		Document document = XmlHelper.parse(in);
		productList = PRODUCT_XML_MANAGER.parseList(document, "product");
		return productList;
	}

}
