package com.twilio.sms.utils;

import com.twilio.http.TwilioRestClient;

import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;


public class MessageSender {
	private final TwilioRestClient client;
	public MessageSender()
	{
		client = new TwilioRestClient.Builder(
                Credentials.getAuth_Sid(),
                Credentials.getAuth_token()).build();
		//System.out.println(Credentials.getAuth_Sid());
	}

	
	 public  void sendMessage(String Message,String Number)
	 {
		 new MessageCreator(
	                new PhoneNumber(Number),
	                new PhoneNumber(Credentials.getNumber()),
	                Message
	        ).create(client);
	 }
}
