package com.godoro.web.client;

import java.io.InputStream;
import java.util.List;

import org.w3c.dom.Document;

import com.godoro.core.utils.WebHelper;
import com.godoro.core.utils.XmlHelper;
import com.godoro.database.entity.Address;
import com.godoro.xml.AddressXmlManager;

public class AddressClientManager extends BaseClientManager<Address>{
	private final static AddressXmlManager ADDRESS_XML_MANAGER = new AddressXmlManager();
	private final static String FIND_ADDRESS = "http://localhost:8080/Website/api/findAddress?id=%d";
	private final static String LİST_ADDRESS = "http://localhost:8080/Website/admin/adresses";
	private final static String INSERT_ADDRESS = "http://localhost:8080/Website/api/addAddress" ;
	private final static String UPDATE_ADDRESS = "http://localhost:8080/Website/api/updateAddress" ;
	private final static String DELETE_ADDRESS = "http://localhost:8080/Website/api/deleteAddress?id=%d";
	
	
	protected String getFindAddress(long addresId) {
		return String.format(FIND_ADDRESS,addresId);
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

	protected String getDeleteAddress(long addresId) {
		return String.format(DELETE_ADDRESS,addresId);
	}

	@Override
	protected Document getDocument(Address address) throws Exception {
		Document document = null;
		document = ADDRESS_XML_MANAGER.format(address ,"address");
		return document;
	}

	@Override
	protected Address getEntity(Document document) {
		Address address = null;
		address = ADDRESS_XML_MANAGER.parse(document);
		return address;
	}

	@Override
	protected List<Address> getEntityList(Document document) {
		List<Address> addressList = null;
		addressList =  ADDRESS_XML_MANAGER.parseList(document, "address");
		return addressList;
	}
	
	public Address findByConsumer(long consumerId) throws Exception{
		Address address = null;
		String addressLine = String.format("http://localhost:8080/Website/api/findAddressByConsumer?id=%d",consumerId);
		InputStream in = WebHelper.get(addressLine);
		Document document = XmlHelper.parse(in);
		address = ADDRESS_XML_MANAGER.parse(document);
		return address;
	}



	
	

}
