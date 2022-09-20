package com.godoro.web.client;

import java.io.InputStream;
import java.util.List;

import org.w3c.dom.Document;

import com.godoro.core.utils.WebHelper;
import com.godoro.core.utils.XmlHelper;
import com.godoro.database.entity.Consumer;
import com.godoro.xml.ConsumerXmlManager;

public class ConsumerClientManager extends BaseClientManager<Consumer>{
	private final static ConsumerXmlManager CONSUMER_XML_MANAGER = new ConsumerXmlManager();
	private final static String FIND_ADDRESS = "http://localhost:8080/Website/api/findConsumer?id=%d";
	private final static String LİST_ADDRESS = "http://localhost:8080/Website/admin/consumers";
	private final static String INSERT_ADDRESS = "http://localhost:8080/Website/api/addConsumer" ;
	private final static String UPDATE_ADDRESS = "http://localhost:8080/Website/api/updateConsumer" ;
	private final static String DELETE_ADDRESS = "http://localhost:8080/Website/admin/deleteConsumer?id=%d";
	
	protected String getFindAddress(long consumerId) {
		return String.format(FIND_ADDRESS,consumerId);
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

	protected String getDeleteAddress(long consumerId) {
		return String.format(DELETE_ADDRESS,consumerId);
	}

	@Override
	protected Document getDocument(Consumer consumer) throws Exception {
		Document document = null;
		document = CONSUMER_XML_MANAGER.format(consumer ,"consumer");
		return document;
	}

	@Override
	protected Consumer getEntity(Document document) {
		Consumer consumer = null;
		consumer = CONSUMER_XML_MANAGER.parse(document);
		return consumer;
	}

	@Override
	protected List<Consumer> getEntityList(Document document) {
		List<Consumer> consumerList = null;
		consumerList =  CONSUMER_XML_MANAGER.parseList(document, "consumer");
		return consumerList;
	}
	
	public Consumer find(String consumerName) throws Exception {
		Consumer consumer = null;
		String address = String.format("http://localhost:8080/Website/api/findConsumerByName?name=%s",consumerName);
		InputStream in = WebHelper.get(address);
		Document document = XmlHelper.parse(in);
		consumer = CONSUMER_XML_MANAGER.parse(document);
		return consumer;
	}
}
