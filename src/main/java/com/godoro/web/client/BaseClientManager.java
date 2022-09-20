package com.godoro.web.client;

import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

import org.w3c.dom.Document;

import com.godoro.core.utils.StreamHelper;
import com.godoro.core.utils.WebHelper;
import com.godoro.core.utils.XmlHelper;

abstract public class BaseClientManager<E> {
	
	public List<E> list() throws Exception{
		List<E> entityList = null;
		String address = getListAddress();
		InputStream in = WebHelper.get(address);
		Document document = XmlHelper.parse(in);
		entityList = getEntityList(document);
		return entityList;
	}

	public String insert(E entity) throws Exception  {
		String address = getInsertAddress();
		Document document = getDocument(entity);
		URLConnection connection = WebHelper.connect(address);
		XmlHelper.dump(document, connection.getOutputStream());
		String result = StreamHelper.read(connection.getInputStream());
		return result;
	}
	
	public E find(long entityId) throws Exception {
		E entity = null;
		String address = getFindAddress(entityId);
		InputStream in = WebHelper.get(address);
		Document document = XmlHelper.parse(in);
		entity = getEntity(document);
		return entity;
	}
	
	public String update(E entity) throws Exception{
		String address = getUpdateAddress();
		Document document = getDocument(entity);
		URLConnection connection = WebHelper.connect(address);
		XmlHelper.dump(document, connection.getOutputStream());
		String result = StreamHelper.read(connection.getInputStream());
		return result;
	}
	
	public String delete(long entityId) throws Exception{
		String address =getDeleteAddress(entityId);
		InputStream in = WebHelper.get(address);
		String result = StreamHelper.read(in);
		return result;

	}
	
	abstract protected String getFindAddress(long entityId);
	abstract protected String getListAddress();
	abstract protected String getInsertAddress();
	abstract protected String getUpdateAddress();
	abstract protected String getDeleteAddress(long entityId);
	abstract protected Document getDocument(E entity) throws Exception;
	abstract protected E getEntity(Document document);
	abstract protected List<E> getEntityList(Document document);
	
}
