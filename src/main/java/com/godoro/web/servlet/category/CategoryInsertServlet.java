package com.godoro.web.servlet.category;

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
import com.godoro.database.manager.CategoryManager;
import com.godoro.xml.CategoryXmlManager;



@WebServlet("/admin/addCategory")
public class CategoryInsertServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Document document = null;
		try {
			document = XmlHelper.parse(request.getInputStream());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		CategoryXmlManager categoryXmlManager = new CategoryXmlManager();
		Category category =categoryXmlManager.parse(document);
		
		CategoryManager categoryManager = new CategoryManager();
		boolean inserted = false;
		try {
			inserted = categoryManager.insert(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = inserted
				?"Eklendi"
				:"Eklenmedi";
		StreamHelper.write(response.getOutputStream(),result);
		
	}

	
}
