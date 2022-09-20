package com.godoro.web.servlet.consumer;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.godoro.core.utils.StreamHelper;
import com.godoro.core.utils.XmlHelper;
import com.godoro.database.entity.Consumer;
import com.godoro.database.manager.ConsumerManager;
import com.godoro.xml.ConsumerXmlManager;



@WebServlet("/api/addConsumer")
public class ConsumerInsertServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Document document = null;
		try {
			document = XmlHelper.parse(request.getInputStream());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		ConsumerXmlManager consumerXmlManager = new ConsumerXmlManager();
		Consumer consumer =consumerXmlManager.parse(document);
		
		ConsumerManager consumerManager = new ConsumerManager();
		boolean inserted = false;
		try {
			inserted = consumerManager.insert(consumer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = inserted
				?"Eklendi"
				:"Eklenmedi";
		StreamHelper.write(response.getOutputStream(),result);
		
	}

	
}
