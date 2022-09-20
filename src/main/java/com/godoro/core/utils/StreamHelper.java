package com.godoro.core.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class StreamHelper {
	public static void write(OutputStream out, String output) throws IOException  {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
		writer.write(output+"\r\n");
		writer.flush();
		writer.close();
		out.flush();
		out.close();
	}
	
	public static String read(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader( new InputStreamReader(in,"utf-8"));
		String line;
		StringBuilder builder = new StringBuilder();
		while((line = reader.readLine())!=null){
			builder.append(line).append("\r\n");
		}
		reader.close();
		String text = builder.toString();
		return text;
	}
}
