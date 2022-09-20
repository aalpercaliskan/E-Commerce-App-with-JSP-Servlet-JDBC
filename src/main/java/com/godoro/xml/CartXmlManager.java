package com.godoro.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.godoro.database.entity.Cart;
import com.godoro.database.entity.Consumer;
import com.godoro.database.manager.ConsumerManager;

public class CartXmlManager extends BaseXmlManager<Cart>{

	@Override
	protected Document setEntityElement(Document document, Element element, Cart cart) {
		if(cart != null) {
			element.setAttribute("cartId", Long.toString(cart.getCartId()));
			addSingleElementText(document, element, "totalAmount", cart.getTotalAmount());
			addSingleElementText(document, element, "consumerId", cart.getConsumer().getConsumerId());
		}
		return document;
	}

	@Override
	protected Cart createEntityFromElement(Element element) {
		Cart cart = null;
		
		if(element.hasAttribute("cartId")) {
			long cartId = getAttribute(element, "cartId", 0);
			double totalAmount = getSingleElementText(element, "totalAmount",.0);
			long consumerId =getSingleElementText(element, "consumerId", 0);
			
			cart = new Cart(cartId, totalAmount);
			
			Consumer consumer = null;
			try {
				consumer= getConsumer(consumerId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cart.setConsumer(consumer);
		}
		return cart;
	}
	
	private Consumer getConsumer(long consumerId) throws Exception {
		Consumer consumer = null;
		ConsumerManager consumerManager = new ConsumerManager();
		consumer = consumerManager.find(consumerId);
		return consumer;
	}

}
