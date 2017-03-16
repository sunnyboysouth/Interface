package com.temenos.integration;

import org.codehaus.jettison.json.JSONObject;

public class RippleMessageExtract {
	private String ExtractedValue;

	public RippleMessageExtract() {
	}

	public String ExtractRippleContent(String InputParameters) {

		String[] parts = InputParameters.split("\\##");
		String ActionCode = parts[0];
		String InputMessage = parts[1];

		try {
			switch (ActionCode) {
			case "GET_QUOTE":

				JSONObject json = new JSONObject(InputMessage);
				// String RippleContract = json.getString("contract");
				// JSONObject RippleContractJson = new
				// JSONObject(RippleContract);

				JSONObject RippleContract = new JSONObject(json.getString("contract"));
				JSONObject SendingInfo = new JSONObject(RippleContract.getString("sending_payment"));
				JSONObject SenderInfo = new JSONObject(SendingInfo.getString("sender"));
				JSONObject AmountInfo = new JSONObject(SenderInfo.getString("amount"));

				// System.out.println(json.getJSONObject("contract").getJSONObject("sending_payment").getJSONObject("sender").getJSONObject("amount").get("value"));

				ExtractedValue = AmountInfo.getString("currency") + "_" + AmountInfo.getString("value");
				break;

			case "ACCEPT_QUOTE":
				JSONObject ripple_payment = new JSONObject(InputMessage);
				ExtractedValue = ripple_payment.getString("payment_id");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
// System.out.println("Extracted Value" + ExtractedValue );
		return ExtractedValue;
	}
	
	public String ExtractSubmitBody(String InputParameters) {
		try {
			System.out.println(InputParameters);
			JSONObject RippleQuote = new JSONObject(InputParameters);
			JSONObject RippleContract = new JSONObject(RippleQuote.getString("contract"));
			JSONObject SendingInfo = new JSONObject(RippleContract.getString("sending_payment"));
			ExtractedValue = SendingInfo.toString();
		}	catch (Exception e) {
			e.printStackTrace();
		}
		return ExtractedValue;
	}

}
