package com.godoro.web.servlet.category;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.godoro.core.utils.StreamHelper;
import com.godoro.database.manager.CategoryManager;

@WebServlet("/admin/deleteCategory")
public class CategoryDeleteServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		long categoryId =Long.parseLong(request.getParameter("id"));
		
		CategoryManager categoryManager = new CategoryManager();
		boolean deleted = false;
		try {
			deleted = categoryManager.delete(categoryId);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		String result = deleted
				?"Silindi"
				:"Silinmedi";
		StreamHelper.write(response.getOutputStream(), result);
		
		
	}
}
