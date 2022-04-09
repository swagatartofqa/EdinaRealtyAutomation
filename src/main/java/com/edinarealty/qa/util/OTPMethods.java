package com.edinarealty.qa.util;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

//Example 1:
//How to automate OTP from number | Using Twilio SMS APIs
//https://www.youtube.com/watch?v=VLJIgOi7g2A&t=1511s

//https://www.twilio.com/console/ahoy
//user: erwtest1@gmail.com / whKhWQ:"_k7f6kXN
//trial number: +19378844148

//https://github.com/naveenanimation20/OTPSMSAutomation

//Example 2:
//https://www.vinsguru.com/selenium-webdriver-multi-factor-authentication-sms/
//https://itnext.io/how-to-use-otp-service-in-your-automation-scripts-with-selenium-web-driver-fe81607232b5

public class OTPMethods {
	//Initialize Variable(s)
	public static final String ACCOUNT_SID = "AC8804e95011bea8ee22be6a95b61568e9";
	public static final String AUTH_TOKEN = "db6c30635b4d8025c5efa9d38552c981";
//	private static final String ACCOUNT_SID = "AC9333f96bc3ec22e9d4f16b8c74ab0809";
//	private static final String AUTH_TOKEN = "064c920c4fcb927f4f8ac0ea6b65cd4c";
//	.filter(m -> m.getTo().equals("+19402835138")).map(Message::getBody).findFirst()
	
	public static String getMessage() {
		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals("+19378844148")).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException::new);
	}
	
	public static Stream<Message> getMessages() {
		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
		return StreamSupport.stream(messages.spliterator(), false);
	}
	
	public String outputOTPNumber() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		String smsBody = getMessage();
		String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
		System.out.println(OTPNumber.strip());
		return OTPNumber.strip();
	}
}