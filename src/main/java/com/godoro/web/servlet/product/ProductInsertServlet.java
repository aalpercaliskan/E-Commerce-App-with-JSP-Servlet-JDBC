package com.godoro.web.servlet.product;

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
import com.godoro.database.entity.Category;
import com.godoro.database.entity.Product;
import com.godoro.database.manager.CategoryManager;
import com.godoro.database.manager.ProductManager;
import com.godoro.xml.CategoryXmlManager;
import com.godoro.xml.ProductXmlManager;



@WebServlet("/admin/addProduct")
public class ProductInsertServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Document document = null;
		try {
			document = XmlHelper.parse(request.getInputStream());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		ProductXmlManager productXmlManager = new ProductXmlManager();
		Product product =productXmlManager.parse(document);
		
		ProductManager productManager = new ProductManager();
		boolean inserted = false;
		try {
			inserted = productManager.insert(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = inserted
				?"Eklendi"
				:"Eklenmedi";
		StreamHelper.write(response.getOutputStream(),result);
		
	}

	
}
