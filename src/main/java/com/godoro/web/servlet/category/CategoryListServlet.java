package com.godoro.web.servlet.category;

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
import com.godoro.database.entity.Category;
import com.godoro.database.manager.CategoryManager;
import com.godoro.xml.CategoryXmlManager;

@WebServlet("/api/categories")
public class CategoryListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
		
		CategoryManager categoryManager = new CategoryManager();
		
		List<Category> categoryList = null;
		try {
			categoryList = categoryManager.list();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		CategoryXmlManager categoryXmlManager = new CategoryXmlManager();
		Document document = null;
		try {
			document = categoryXmlManager.format(categoryList, "categories","category");
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
