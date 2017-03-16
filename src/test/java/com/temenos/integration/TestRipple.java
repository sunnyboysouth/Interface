package com.temenos.integration;

import org.junit.Test;

public class TestRipple {

	@Test
	public void test() {
		String Ripple_Url = "https://r714.i.ripple.com/v2/payments/quote?sender_uri=acct:Manoj@r714.i.ripple.com&receiver_uri=acct:Sundar@r712.i.ripple.com&receiver_amount=10.00%2BCHF";
		OAuthToken oa = new OAuthToken();
		String token = oa.getToken();
		CurrencyQuote cq = new CurrencyQuote();
		System.out.println(cq.getRippleQuote(token, Ripple_Url));
		
	}

}
