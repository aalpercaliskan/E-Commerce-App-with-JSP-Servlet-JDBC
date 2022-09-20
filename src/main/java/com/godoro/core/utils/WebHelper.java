package com.godoro.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class WebHelper {
	public static InputStream get(String address) throws IOException {
		URL url = new URL(address);
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		return in;
	}
	
	public static URLConnection connect(String address) throws IOException   {
		URL url = new URL(address);
		URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		return connection;
	}
}
