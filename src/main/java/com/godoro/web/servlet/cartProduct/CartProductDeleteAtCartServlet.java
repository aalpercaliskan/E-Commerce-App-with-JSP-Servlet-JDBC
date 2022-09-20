package com.godoro.web.servlet.cartProduct;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.godoro.core.utils.StreamHelper;
import com.godoro.database.manager.CartProductManager;

@WebServlet("/api/deleteCartProductAtCart")
public class CartProductDeleteAtCartServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		long cartId =Long.parseLong(request.getParameter("id"));
		
		CartProductManager cartProductManager = new CartProductManager();
		boolean deleted = false;
		try {
			deleted = cartProductManager.deleteAtCart(cartId);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		String result = deleted
				?"Silindi"
				:"Silinmedi";
		StreamHelper.write(response.getOutputStream(), result);
		
		
	}
}
