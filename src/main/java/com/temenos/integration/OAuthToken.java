/**
 * 
 */
package com.temenos.integration;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
// import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONObject;
/**
 * @author mjangid
 *
 */
public class OAuthToken {
	private String token_url;
	private String user_name;
	private String password;

	public OAuthToken() {
		token_url = "https://r714.i.ripple.com/v2/oauth/token";
		user_name = "client";
		password = "testing";
	}

	public String getToken() {
		String token = null;
		try {
			URL url = new URL(token_url);
			final byte[] authBytes = new String(user_name + ":" + password).getBytes(StandardCharsets.UTF_8);
			// String encoding = org.apache.commons.codec.binary.Base64.getEncoder().encodeToString(authBytes);
			//String encoding = DatatypeConverter.printBase64Binary(authBytes);
			String encoding = DatatypeConverter.printBase64Binary(authBytes);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			connection.setRequestProperty("Content-type", "application/json");
			
			String input = "{\"grant_type\":\"client_credentials\"}";
	        OutputStream os = connection.getOutputStream();
	        os.write(input.getBytes());
	        os.flush();
	        
			//connection.connect();
			InputStream content = (InputStream) connection.getInputStream();
			JSONObject json = new JSONObject(IOUtils.toString(content));
			token = json.getString("token_type") + " " + json.get("access_token");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
}
