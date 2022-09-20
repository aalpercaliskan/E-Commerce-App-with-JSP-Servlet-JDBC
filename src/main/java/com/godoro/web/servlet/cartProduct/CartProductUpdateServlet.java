package com.godoro.web.servlet.cartProduct;

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
import com.godoro.database.entity.CartProduct;
import com.godoro.database.manager.CartProductManager;
import com.godoro.xml.CartProductXmlManager;

@WebServlet("/api/updateCartProduct")
public class CartProductUpdateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Document document = null;
		try {
			document = XmlHelper.parse(request.getInputStream());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		CartProductXmlManager cartProductXmlManager = new CartProductXmlManager();
		CartProduct cartProduct =cartProductXmlManager.parse(document);
		CartProductManager cartProductManager = new CartProductManager();
		boolean updated = false;
		try {
			updated = cartProductManager.update(cartProduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = updated
				?"Güncellendi"
				:"Güncellenmedi";
		StreamHelper.write(response.getOutputStream(),result);
		
	}
}
