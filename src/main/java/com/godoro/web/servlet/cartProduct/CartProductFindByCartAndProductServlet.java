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

@WebServlet("/api/cartProduct")
public class CartProductFindByCartAndProductServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		long productId =Long.parseLong(request.getParameter("productId"));
		long cartId =Long.parseLong(request.getParameter("cartId"));
		
		CartProductManager cartProductManager = new CartProductManager();
		CartProduct cartProduct = null;
		try {
			cartProduct = cartProductManager.find(productId,cartId);
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
