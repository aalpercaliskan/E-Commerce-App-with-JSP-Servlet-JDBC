package com.godoro.web.servlet.cart;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.godoro.core.utils.StreamHelper;
import com.godoro.database.manager.CartManager;

@WebServlet("/admin/deleteCart")
public class CartDeleteServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		long cartId =Long.parseLong(request.getParameter("id"));
		
		CartManager cartManager = new CartManager();
		boolean deleted = false;
		try {
			deleted = cartManager.delete(cartId);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		String result = deleted
				?"Silindi"
				:"Silinmedi";
		StreamHelper.write(response.getOutputStream(), result);
		
		
	}
}
