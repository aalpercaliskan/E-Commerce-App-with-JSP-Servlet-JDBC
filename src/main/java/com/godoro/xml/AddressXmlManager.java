package com.godoro.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.godoro.database.entity.Address;
import com.godoro.database.entity.Consumer;
import com.godoro.database.manager.ConsumerManager;

public class AddressXmlManager extends BaseXmlManager<Address>{

	@Override
	protected Document setEntityElement(Document document, Element element, Address address) {
		if(address != null) {
			element.setAttribute("addressId", Long.toString(address.getAddressId()));
			addSingleElementText(document, element, "provinceName",address.getProvinceName());
			addSingleElementText(document, element, "addressLine1", address.getAddressLine1());
			addSingleElementText(document, element, "addressLine2", address.getAddressLine2());
			addSingleElementText(document, element, "consumerId", address.getConsumer().getConsumerId());
		}
		return document;
	}

	@Override
	protected Address createEntityFromElement(Element element) {
		Address address = null;
		
		if(element.hasAttribute("addressId")) {
			long addressId = getAttribute(element, "addressId", 0);
			String provinceName = getSingleElementText(element, "provinceName","");
			String addressLine1 = getSingleElementText(element, "addressLine1","");
			String addressLine2 = getSingleElementText(element, "addressLine2","");
			long consumerId =getSingleElementText(element, "consumerId", 0);
			
			address = new Address(addressId, provinceName, addressLine1, addressLine2);
			
			Consumer consumer = null;
			try {
				consumer= getConsumer(consumerId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			address.setConsumer(consumer);
		}
		return address;
	}
	
	private Consumer getConsumer(long consumerId) throws Exception {
		Consumer consumer = null;
		ConsumerManager consumerManager = new ConsumerManager();
		consumer = consumerManager.find(consumerId);
		return consumer;
	}

}
