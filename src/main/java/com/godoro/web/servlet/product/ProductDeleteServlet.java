package com.godoro.web.servlet.product;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.godoro.core.utils.StreamHelper;
import com.godoro.database.manager.ProductManager;

@WebServlet("/admin/deleteProduct")
public class ProductDeleteServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		long productId =Long.parseLong(request.getParameter("id"));
		
		ProductManager productManager = new ProductManager();
		boolean deleted = false;
		try {
			deleted = productManager.delete(productId);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		String result = deleted
				?"Silindi"
				:"Silinmedi";
		StreamHelper.write(response.getOutputStream(), result);
		
		
	}
}
