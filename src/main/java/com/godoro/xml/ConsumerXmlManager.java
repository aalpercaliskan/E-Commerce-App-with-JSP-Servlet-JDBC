package com.godoro.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.godoro.database.entity.Consumer;

public class ConsumerXmlManager extends BaseXmlManager<Consumer> {

	@Override
	protected Document setEntityElement(Document document, Element element, Consumer consumer) {
		if(consumer != null) {
			element.setAttribute("consumerId", Long.toString(consumer.getConsumerId()));
			addSingleElementText(document, element, "consumerName", consumer.getConsumerName());
			addSingleElementText(document, element, "consumerPassword", consumer.getConsumerPassword());
		}
		return document;
	}

	@Override
	protected Consumer createEntityFromElement(Element element) {
		Consumer consumer = null;
		
		if(element.hasAttribute("consumerId")) {
			long consumerId = getAttribute(element, "consumerId", 0);
			String consumerName = getSingleElementText(element, "consumerName","");
			String consumerPassword = getSingleElementText(element, "consumerPassword","");
			consumer = new Consumer(consumerId, consumerName, consumerPassword);
		}
		return consumer;
	}

}
