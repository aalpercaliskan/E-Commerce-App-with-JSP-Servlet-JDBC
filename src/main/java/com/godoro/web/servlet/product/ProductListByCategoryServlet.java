package com.godoro.web.servlet.product;

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
import com.godoro.database.entity.Product;
import com.godoro.database.manager.ProductManager;
import com.godoro.xml.ProductXmlManager;

@WebServlet("/api/products")
public class ProductListByCategoryServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		long categoryId =Long.parseLong(request.getParameter("id"));
		
		ProductManager productManager = new ProductManager();
		
		List<Product> playerList = null;
		try {
			playerList = productManager.list(categoryId);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		ProductXmlManager productXmlManager = new ProductXmlManager();
		Document document = null;
		try {
			document = productXmlManager.format(playerList, "products", "product");
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
