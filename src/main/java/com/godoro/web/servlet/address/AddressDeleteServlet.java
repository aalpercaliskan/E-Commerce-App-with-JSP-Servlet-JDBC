package com.godoro.web.servlet.address;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.godoro.core.utils.StreamHelper;
import com.godoro.database.manager.AddressManager;

@WebServlet("/api/deleteAddress")
public class AddressDeleteServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		long addressId =Long.parseLong(request.getParameter("id"));
		
		AddressManager addressManager = new AddressManager();
		boolean deleted = false;
		try {
			deleted = addressManager.delete(addressId);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		String result = deleted
				?"Silindi"
				:"Silinmedi";
		StreamHelper.write(response.getOutputStream(), result);
		
		
	}
}
