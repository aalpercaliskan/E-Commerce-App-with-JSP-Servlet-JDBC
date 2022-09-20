package com.godoro.web.client;

import java.io.InputStream;
import java.util.List;

import org.w3c.dom.Document;

import com.godoro.core.utils.StreamHelper;
import com.godoro.core.utils.WebHelper;
import com.godoro.core.utils.XmlHelper;
import com.godoro.database.entity.CartProduct;
import com.godoro.xml.CartProductXmlManager;

public class CartProductClientManager extends BaseClientManager<CartProduct>{
	private final static CartProductXmlManager CART_PRODUCT_XML_MANAGER = new CartProductXmlManager();
	private final static String FIND_ADDRESS = "http://localhost:8080/Website/api/findCartProduct?id=%d";
	private final static String LİST_ADDRESS = "http://localhost:8080/Website/admin/allCartProducts";
	private final static String INSERT_ADDRESS = "http://localhost:8080/Website/api/addCartProduct" ;
	private final static String UPDATE_ADDRESS = "http://localhost:8080/Website/api/updateCartProduct" ;
	private final static String DELETE_ADDRESS = "http://localhost:8080/Website/api/deleteCartProduct?id=%d";
	
	
	protected String getFindAddress(long cartProductId) {
		return String.format(FIND_ADDRESS,cartProductId);
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

	protected String getDeleteAddress(long cartProductId) {
		return String.format(DELETE_ADDRESS,cartProductId);
	}

	@Override
	protected Document getDocument(CartProduct cartProduct) throws Exception {
		Document document = null;
		document = CART_PRODUCT_XML_MANAGER.format(cartProduct ,"cartProduct");
		return document;
	}

	@Override
	protected CartProduct getEntity(Document document) {
		CartProduct cartProduct = null;
		cartProduct = CART_PRODUCT_XML_MANAGER.parse(document);
		return cartProduct;
	}

	@Override
	protected List<CartProduct> getEntityList(Document document) {
		List<CartProduct> cartProductList = null;
		cartProductList =  CART_PRODUCT_XML_MANAGER.parseList(document, "cartProduct");
		return cartProductList;
	}
	
	public List<CartProduct> list(long cartId) throws Exception{
		List<CartProduct> cartProducts = null;
		String address = String.format("http://localhost:8080/Website/api/cartProducts?id=%d",cartId);
		InputStream in = WebHelper.get(address);
		Document document = XmlHelper.parse(in);
		cartProducts = CART_PRODUCT_XML_MANAGER.parseList(document, "cartProduct");
		return cartProducts;
	}
	
	public CartProduct find(long productId, long cartId) throws Exception {
		CartProduct cartProduct = null;
		String address = String.format("http://localhost:8080/Website/api/cartProduct?productId=%d&cartId=%d",productId,cartId);
		InputStream in = WebHelper.get(address);
		Document document = XmlHelper.parse(in);
		cartProduct = getEntity(document);
		return cartProduct;
	}
	

	public String deleteAtCart(long cartId) throws Exception{
		String address = String.format("http://localhost:8080/Website/api/deleteCartProductAtCart?id=%d",cartId);
		InputStream in = WebHelper.get(address);
		String result = StreamHelper.read(in);
		return result;

	}
	
}
