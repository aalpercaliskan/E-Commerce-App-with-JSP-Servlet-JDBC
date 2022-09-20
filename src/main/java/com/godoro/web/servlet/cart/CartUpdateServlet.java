package com.godoro.web.servlet.cart;

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
import com.godoro.database.entity.Cart;
import com.godoro.database.manager.CartManager;
import com.godoro.xml.CartXmlManager;

@WebServlet("/admin/updateCart")
public class CartUpdateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Document document = null;
		try {
			document = XmlHelper.parse(request.getInputStream());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		CartXmlManager cartXmlManager = new CartXmlManager();
		Cart cart =cartXmlManager.parse(document);
		CartManager cartManager = new CartManager();
		boolean updated = false;
		try {
			updated = cartManager.update(cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = updated
				?"Güncellendi"
				:"Güncellenmedi";
		StreamHelper.write(response.getOutputStream(),result);
		
	}
}
