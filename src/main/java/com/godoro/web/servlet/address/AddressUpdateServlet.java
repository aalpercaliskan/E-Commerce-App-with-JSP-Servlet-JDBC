package com.godoro.web.servlet.address;

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
import com.godoro.database.entity.Address;
import com.godoro.database.manager.AddressManager;
import com.godoro.xml.AddressXmlManager;

@WebServlet("/api/updateAddress")
public class AddressUpdateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Document document = null;
		try {
			document = XmlHelper.parse(request.getInputStream());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		AddressXmlManager addressXmlManager = new AddressXmlManager();
		Address address =addressXmlManager.parse(document);
		AddressManager addressManager = new AddressManager();
		boolean updated = false;
		try {
			updated = addressManager.update(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = updated
				?"Güncellendi"
				:"Güncellenmedi";
		StreamHelper.write(response.getOutputStream(),result);
		
	}
}
