package com.godoro.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlHelper {
	private static DocumentBuilderFactory factory;
	public static DocumentBuilderFactory getFactory() {
		if(factory == null) {
			factory = DocumentBuilderFactory.newInstance();
		}
		return factory;
	}
	
	public static void dump(Document document, OutputStream out) throws TransformerException, IOException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		DOMSource data = new DOMSource(document);
		StreamResult result = new StreamResult(out);
		transformer.transform(data, result);
		out.close();
	}
	
	public static Document parse(InputStream in) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilder builder = getFactory().newDocumentBuilder();
		Document document = builder.parse(in);
		return document;
	}
	
	public static String getSingleElementText(Element parent, String tag,String defaultValue) {
		NodeList elementList = parent.getElementsByTagName(tag);
		if(elementList.getLength()==0) {
			return defaultValue;
		}
		return elementList.item(0).getTextContent();
	}
	
	public static double getSingleElementText(Element parent, String tag,double defaultValue) {
		String string = getSingleElementText(parent, tag, Double.toString(defaultValue));
		return Double.parseDouble(string);
	}
	
	public static long getSingleElementText(Element parent, String tag,long defaultValue) {
		String string = getSingleElementText(parent, tag, Long.toString(defaultValue));
		return Long.parseLong(string);
	}
	
	public static int getSingleElementText(Element parent, String tag,int defaultValue) {
		String string = getSingleElementText(parent, tag, Long.toString(defaultValue));
		return Integer.parseInt(string);
	}
	
	public static String getAttribute(Element element,String name, String defaultValue) {
		if(!element.hasAttribute(name)) {
			return defaultValue;
		}
		return element.getAttribute(name);
	}
	
	public static Long getAttribute(Element element,String name, long defaultValue) {
		String string = getAttribute(element, name, Long.toString(defaultValue));
		return Long.parseLong(string);
	}
	
	public static void addSingleElementText(Document document, Element parent,String tag, String content) {
		Element name = document.createElement(tag);
		name.setTextContent(content);
		parent.appendChild(name);
	}
	
	public static void addSingleElementText(Document document, Element parent,String tag, double content) {
		String string = Double.toString(content);
		addSingleElementText(document, parent, tag, string);
		
	}
	
	public static void addSingleElementText(Document document, Element parent,String tag, long content) {
		String string = Long.toString(content);
		addSingleElementText(document, parent, tag, string);
		
	}
	public static void addSingleElementText(Document document, Element parent,String tag, int content) {
		String string = Integer.toString(content);
		addSingleElementText(document, parent, tag, string);
		
	}
}
