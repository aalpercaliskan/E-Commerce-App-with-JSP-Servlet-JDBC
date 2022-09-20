package com.godoro.web.servlet.consumer;

import java.io.IOException;

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

@WebServlet("/api/findConsumer")
public class ConsumerFindServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		long consumerId =Long.parseLong(request.getParameter("id"));
		
		ConsumerManager consumerManager = new ConsumerManager();
		Consumer consumer = null;
		try {
			consumer = consumerManager.find(consumerId);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		

		Document document = null;
		try {
			ConsumerXmlManager consumerXmlManager = new ConsumerXmlManager();
			document = consumerXmlManager.format(consumer,"consumer");
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
