package com.temenos.integration;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class Payments {

	private String token_url;
	public Payments() {
		token_url = "https://r714.i.ripple.com/v2/payments";
	}

	public String getAllPayments(String authToken) {
		try {
			URL url = new URL(token_url);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", authToken);
			connection.setRequestProperty("Content-type", "application/json");

			InputStream content = (InputStream) connection.getInputStream();
			return IOUtils.toString(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
}
