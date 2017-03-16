package com.temenos.integration;

// import java.io.InputStream;
// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;

// import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONObject;

public class RippleConnect {

	private String ReturnQuote;
	public String Ripple_Quote="";
	public RippleConnect() {}

		public static void main(String[] args) {
			OAuthToken oa = new OAuthToken();
			String token = oa.getToken();
			System.out.println( token );
			
			Payments p = new Payments();
			System.out.println( p.getAllPayments(token));
			
			CurrencyQuote quote = new CurrencyQuote();
			System.out.println( quote.getQuote(token));
		}
		
		public String DeliverRippleMessage(String InputParameters)
		{
			String[] parts = InputParameters.split("\\##");
			final String ActionCode = parts[0];
			// final String UserName = parts[1];
			// final String Ripple_Password = parts[2];
			final String Ripple_Url = parts[3];
			final String Body_TxnId = parts[4];
			
			try{
			OAuthToken oa = new OAuthToken();
			String token = oa.getToken();
			// System.out.println( token );
			
			switch (ActionCode){
			case "GET_QUOTE":
		    // System.out.println("GET_CODE");
			CurrencyQuote quote = new CurrencyQuote();
			JSONObject Ripple_Quote = quote.getRippleQuote(token,Ripple_Url);
			// System.out.println(Ripple_Quote);
			
			ReturnQuote = Ripple_Quote.toString();
			//String Ripple_result = j3.getString("value") +"_"+ j3.getString("currency") + "," + j4.getString("base")+"_"+j4.getString("counter")+"_"+j4.getString("rate");
			//System.out.println(Ripple_result);
			
			break;			
			
			case "ACCEPT_QUOTE":
				CurrencyQuote acceptedQuote = new CurrencyQuote();
				JSONObject Ripple_Response = acceptedQuote.acceptRippleQuote(token,Ripple_Url,Body_TxnId);
				ReturnQuote = Ripple_Response.toString();
				break;
			
			case "SUBMIT_PAYMENT": 
				CurrencyQuote submitPayment = new CurrencyQuote();
				RippleMessageExtract rippleMessage = new RippleMessageExtract();
				String submitBody = rippleMessage.ExtractSubmitBody(Body_TxnId);
				JSONObject Ripple_Payment_Response = submitPayment.acceptRippleQuote(token,Ripple_Url,submitBody);
				ReturnQuote = Ripple_Payment_Response.toString();
				break;				
			}			
			}catch(Exception e){
				e.printStackTrace();
			}	
			return ReturnQuote;	
			
		}
	    public String TestFunction(String pData) {
	        System.out.println("Java says: Hello world to");

	        pData = "calljHelloWorld: function worked";

	        return pData;
	    }

}
