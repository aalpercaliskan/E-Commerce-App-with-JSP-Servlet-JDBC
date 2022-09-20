package com.godoro.web.servlet.consumer;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.godoro.core.utils.StreamHelper;
import com.godoro.database.manager.ConsumerManager;

@WebServlet("/admin/deleteConsumer")
public class ConsumerDeleteServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		long consumerId =Long.parseLong(request.getParameter("id"));
		
		ConsumerManager consumerManager = new ConsumerManager();
		boolean deleted = false;
		try {
			deleted = consumerManager.delete(consumerId);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		String result = deleted
				?"Silindi"
				:"Silinmedi";
		StreamHelper.write(response.getOutputStream(), result);
		
		
	}
}
