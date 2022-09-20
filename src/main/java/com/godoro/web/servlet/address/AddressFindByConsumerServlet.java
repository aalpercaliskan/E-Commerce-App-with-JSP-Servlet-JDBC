package com.godoro.web.servlet.address;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.godoro.core.utils.XmlHelper;
import com.godoro.database.entity.Address;
import com.godoro.database.manager.AddressManager;
import com.godoro.xml.AddressXmlManager;

@WebServlet("/api/findAddressByConsumer")
public class AddressFindByConsumerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		long consumerId =Long.parseLong(request.getParameter("id"));
		
		AddressManager addressManager = new AddressManager();
		Address address = null;
		try {
			address = addressManager.findByConsumer(consumerId);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		

		Document document = null;
		try {
			AddressXmlManager addressXmlManager = new AddressXmlManager();
			document = addressXmlManager.format(address,"address");
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
