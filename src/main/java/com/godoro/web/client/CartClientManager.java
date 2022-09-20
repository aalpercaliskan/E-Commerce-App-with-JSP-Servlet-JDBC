package com.godoro.web.client;

import java.io.InputStream;
import java.util.List;

import org.w3c.dom.Document;

import com.godoro.core.utils.WebHelper;
import com.godoro.core.utils.XmlHelper;
import com.godoro.database.entity.Cart;
import com.godoro.xml.CartXmlManager;

public class CartClientManager extends BaseClientManager<Cart>{
	private final static CartXmlManager CART_XML_MANAGER = new CartXmlManager();
	private final static String FIND_ADDRESS = "http://localhost:8080/Website/api/findCart?id=%d";
	private final static String LİST_ADDRESS = "http://localhost:8080/Website/admin/carts";
	private final static String INSERT_ADDRESS = "http://localhost:8080/Website/api/addCart" ;
	private final static String UPDATE_ADDRESS = "http://localhost:8080/Website/admin/updateCart" ;
	private final static String DELETE_ADDRESS = "http://localhost:8080/Website/admin/deleteCart?id=%d";
	
	
	protected String getFindAddress(long cartId) {
		return String.format(FIND_ADDRESS,cartId);
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

	protected String getDeleteAddress(long cartId) {
		return String.format(DELETE_ADDRESS,cartId);
	}

	@Override
	protected Document getDocument(Cart cart) throws Exception {
		Document document = null;
		document = CART_XML_MANAGER.format(cart ,"cart");
		return document;
	}

	@Override
	protected Cart getEntity(Document document) {
		Cart cart = null;
		cart = CART_XML_MANAGER.parse(document);
		return cart;
	}

	@Override
	protected List<Cart> getEntityList(Document document) {
		List<Cart> cartList = null;
		cartList =  CART_XML_MANAGER.parseList(document, "cart");
		return cartList;
	}
	
	public Cart findByConsumer(long consumerId) throws Exception{
		Cart cart = null;
		String address = String.format("http://localhost:8080/Website/api/findCartByConsumer?id=%d",consumerId);
		InputStream in = WebHelper.get(address);
		Document document = XmlHelper.parse(in);
		cart = CART_XML_MANAGER.parse(document);
		return cart;
	}



	
	

}
