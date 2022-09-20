package com.godoro.web.servlet.cartProduct;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.godoro.core.utils.XmlHelper;
import com.godoro.database.entity.CartProduct;
import com.godoro.database.manager.CartProductManager;
import com.godoro.xml.CartProductXmlManager;

@WebServlet("/api/findCartProduct")
public class CartProductFindServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		long cartProductId =Long.parseLong(request.getParameter("id"));
		
		CartProductManager cartProductManager = new CartProductManager();
		CartProduct cartProduct = null;
		try {
			cartProduct = cartProductManager.find(cartProductId);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		

		Document document = null;
		try {
			CartProductXmlManager cartProductXmlManager = new CartProductXmlManager();
			document = cartProductXmlManager.format(cartProduct,"cartProduct");
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
