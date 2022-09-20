package com.godoro.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.godoro.database.entity.Cart;
import com.godoro.database.entity.CartProduct;
import com.godoro.database.entity.Product;
import com.godoro.database.manager.CartManager;
import com.godoro.database.manager.ProductManager;

public class CartProductXmlManager extends BaseXmlManager<CartProduct> {
	
	@Override
	protected Document setEntityElement(Document document, Element element, CartProduct cartProduct) {
		if(cartProduct != null) {
			element.setAttribute("cartProductId", Long.toString(cartProduct.getCartProductId()));
			addSingleElementText(document, element, "salesQuantity", cartProduct.getSalesQuantity());
			addSingleElementText(document, element, "salesPrice", cartProduct.getSalesPrice());
			addSingleElementText(document, element, "productId", cartProduct.getProduct().getProductId());
			addSingleElementText(document, element, "cartId", cartProduct.getCart().getCartId());
		}
		return document;
	}

	@Override
	protected CartProduct createEntityFromElement(Element element) {
		CartProduct cartProduct = null;
		
		if(element.hasAttribute("cartProductId")) {
			long cartProductId = getAttribute(element, "cartProductId", 0);
			int salesQuantity = getSingleElementText(element, "salesQuantity",0);
			double salesPrice =getSingleElementText(element, "salesPrice", .0);
			long productId =getSingleElementText(element, "productId", 0);
			long cartId =getSingleElementText(element, "cartId", 0);
			
			cartProduct = new CartProduct(cartProductId, salesQuantity, salesPrice);
			
			Product product = null;
			Cart cart = null;
			try {
				product = getProduct(productId);
				cart = getCart(cartId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cartProduct.setProduct(product);
			cartProduct.setCart(cart);
		}
		return cartProduct;
	}
	
	private Product getProduct(long productId) throws Exception {
		Product product = null;
		ProductManager productManager = new ProductManager();
		product = productManager.find(productId);
		return product;
	}
	
	private Cart getCart(long cartId) throws Exception {
		Cart cart = null;
		CartManager cartManager = new CartManager();
		cart = cartManager.find(cartId);
		return cart;
	}



	
}
