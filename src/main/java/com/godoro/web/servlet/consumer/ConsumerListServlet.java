package com.godoro.web.servlet.consumer;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.godoro.core.utils.XmlHelper;
import com.godoro.database.entity.Consumer;
import com.godoro.database.manager.ConsumerManager;
import com.godoro.xml.ConsumerXmlManager;

@WebServlet("/admin/consumers")
public class ConsumerListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
		
		ConsumerManager consumerManager = new ConsumerManager();
		
		List<Consumer> consumerList = null;
		try {
			consumerList = consumerManager.list();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		ConsumerXmlManager consumerXmlManager = new ConsumerXmlManager();
		Document document = null;
		try {
			document = consumerXmlManager.format(consumerList, "consumers","consumer");
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		response.setContentType("application/xml; charset=UTF-8");
		try {
			XmlHelper.dump(document,response.getOutputStream());
		} catch (TransformerException | IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
