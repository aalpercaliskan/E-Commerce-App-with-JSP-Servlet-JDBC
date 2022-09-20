package com.godoro.web.servlet.address;

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
import com.godoro.database.entity.Address;
import com.godoro.database.manager.AddressManager;
import com.godoro.xml.AddressXmlManager;

@WebServlet("/admin/addresses")
public class AddressListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
		
		AddressManager addressManager = new AddressManager();
		
		List<Address> addressList = null;
		try {
			addressList = addressManager.list();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		AddressXmlManager addressXmlManager = new AddressXmlManager();
		Document document = null;
		try {
			document = addressXmlManager.format(addressList, "addresses","address");
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
