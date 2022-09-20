package com.godoro.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.godoro.core.utils.XmlHelper;

abstract public class BaseXmlManager<E> extends XmlHelper{

	public  Document format(E entity, String entityName) throws ParserConfigurationException {
		DocumentBuilder builder = getFactory().newDocumentBuilder();
		Document document =  builder.newDocument();
		
		Element element = document.createElement(entityName);
		document.appendChild(element);
		document = setEntityElement(document,element,entity);
		
		return document;
	}
	
	protected  abstract Document setEntityElement(Document document, Element element,  E entity);
	
	public Document format(List<E> entityList, String listName, String entityName) throws ParserConfigurationException {
		DocumentBuilder builder = getFactory().newDocumentBuilder();
		Document document =  builder.newDocument();
		Element parent = document.createElement(listName);
		document.appendChild(parent);
		
		if(entityList != null) {
			for(E entity : entityList) {
				Element child = document.createElement(entityName);
				parent.appendChild(child);
				document = setEntityElement(document,child,entity);
			}
		}
		
		return document;
	}
	
	protected abstract E createEntityFromElement(Element element);
	
	public E parse(Document document) {
		Element element = document.getDocumentElement();
		E entity = createEntityFromElement(element);
		return entity;
	}
	
	public List<E> parseList(Document document, String entityName){
		List<E> entityList = new ArrayList<>();
		Element list = document.getDocumentElement();
		NodeList nodeList = list.getElementsByTagName(entityName);
		if(nodeList != null) {
			for(int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element)nodeList.item(i);
				E entity = createEntityFromElement(element);
				entityList.add(entity);
			}
		}
		
		
		return entityList;
	}
}
