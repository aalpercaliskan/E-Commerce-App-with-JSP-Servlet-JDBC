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

@WebServlet("/api/updateConsumer")
public class ConsumerUpdateServlet extends HttpServlet {
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
		boolean updated = false;
		try {
			updated = consumerManager.update(consumer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = updated
				?"Güncellendi"
				:"Güncellenmedi";
		StreamHelper.write(response.getOutputStream(),result);
		
	}
}
