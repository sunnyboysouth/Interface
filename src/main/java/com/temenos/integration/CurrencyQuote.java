package com.temenos.integration;

// import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
// import java.net.MalformedURLException;
// import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONObject;

public class CurrencyQuote {

	private String sender_uri;
	private String receiver_uri;
	private int receiver_amount;
	private String currency;
	private String quoteUrl;
	JSONObject json = null;

	public CurrencyQuote() {
	}

	public CurrencyQuote(String input_parameters) {
		// quoteUrl = "https://r714.i.ripple.com/v2/payments/quote";
		// sender_uri = "1880328@r714.i.ripple.com";
		// receiver_uri = "1920456@r712.i.ripple.com";
		// receiver_amount = 10;
		// currency = "CHF";
		String[] parts = input_parameters.split("\\##");
		quoteUrl = parts[3];
		sender_uri = parts[1];
		receiver_uri = parts[2];
		receiver_amount = Integer.valueOf(parts[3]);
		currency = parts[4];
	}

	@Override
	public String toString() {
		return quoteUrl + "?sender_uri=acct:" + sender_uri + "&receiver_uri=acct:" + receiver_uri + "&receiver_amount="
				+ receiver_amount + "%2B" + currency;
	}

	public String getQuote(String authToken) {
		try {
			URL url = new URL(toString());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", authToken);
			connection.setRequestProperty("Content-type", "application/json");
			connection.connect();
			InputStream content = (InputStream) connection.getInputStream();
			return IOUtils.toString(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject getRippleQuote(String authToken, String Ripple_Url) {
		try {

			System.out.println("Token " + authToken);
			URL url = new URL(Ripple_Url);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", authToken);
			connection.setRequestProperty("Content-type", "application/json");
			connection.connect();
			InputStream content = (InputStream) connection.getInputStream();
			String response = IOUtils.toString(content);
			response = response.substring(1, response.length() - 1);
			json = new JSONObject(response);
			// Ripple_Url = json;
			// return json
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public JSONObject acceptRippleQuote(String authToken, String Ripple_Url, String acceptQuote) {
		try {
		URL url = new URL(Ripple_Url);
        System.out.println(acceptQuote);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Authorization", authToken);
		connection.setRequestProperty("Content-type", "application/json");
        
		OutputStream os = connection.getOutputStream();
        os.write(acceptQuote.getBytes());
        System.out.println(acceptQuote);
        os.flush();
        
		connection.connect();
		InputStream content = (InputStream) connection.getInputStream();
		json = new JSONObject(IOUtils.toString(content));
		// return IOUtils.toString(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
