package com.temenos.integration;

import org.codehaus.jettison.json.JSONObject;

public class FormJsonArray {

	private String ReturnMessage;
	public FormJsonArray() {}
	
	public String getJsonArray(String InputMessage) {
		JSONObject RippleMessage = new JSONObject();
		try {
		
		String[] parts = InputMessage.split("\\##");
		String SenderEntity = parts[0];
		String SenderAmount = parts[1];
		String SenderCcy = parts[2];
		String ReceiverEntity = parts[3];
		String ReceiverAmount = parts[4];
		String ReceiverCcy = parts[5];
		
		JSONObject SenderAmountCcy = new JSONObject();
		JSONObject ReceiverAmountCcy = new JSONObject();
		JSONObject SenderEntityAmount = new JSONObject();
		JSONObject ReceiverEntityAmount = new JSONObject();
		
		SenderAmountCcy.put("value", SenderAmount);
		SenderAmountCcy.put("currency", SenderCcy);
		ReceiverAmountCcy.put("value", ReceiverAmount);
		ReceiverAmountCcy.put("currency", ReceiverCcy);
		
		SenderEntityAmount.put("entity",SenderEntity);
		SenderEntityAmount.put("amount", SenderAmountCcy);
		ReceiverEntityAmount.put("entity", ReceiverEntity);
		ReceiverEntityAmount.put("amount", ReceiverAmountCcy);
		
		
		
		RippleMessage.put("sender", SenderEntityAmount);
		RippleMessage.put("receiver", ReceiverEntityAmount);
		ReturnMessage = RippleMessage.toString();

//		System.out.println(json.getJSONObject("contract").getJSONObject("sending_payment").getJSONObject("sender").getJSONObject("amount").get("value")); 
		
		} catch (Exception e){
			e.printStackTrace();
		}
		return ReturnMessage;
	}	
}
