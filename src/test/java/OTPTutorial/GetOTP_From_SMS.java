package OTPTutorial;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageDeleter;

import io.github.bonigarcia.wdm.WebDriverManager;

// Example 1:
//How to automate OTP from number | Using Twilio SMS APIs
//https://www.youtube.com/watch?v=VLJIgOi7g2A&t=1511s

// https://www.twilio.com/console/ahoy
// user: erwtest1@gmail.com / whKhWQ:"_k7f6kXN > trial number: +19378844148
// user: erwtest2@gmail.com / whKhWQ:"_k7f6kXN > trial number: +16204728435

// https://github.com/naveenanimation20/OTPSMSAutomation

// Example 2:
// https://www.vinsguru.com/selenium-webdriver-multi-factor-authentication-sms/
// https://itnext.io/how-to-use-otp-service-in-your-automation-scripts-with-selenium-web-driver-fe81607232b5

 
public class GetOTP_From_SMS {
	
	// user: erwtest1@gmail.com / whKhWQ:"_k7f6kXN > trial number: +19378844148
	public static final String ACCOUNT_SID = "AC8804e95011bea8ee22be6a95b61568e9";
	public static final String AUTH_TOKEN = "db6c30635b4d8025c5efa9d38552c981";
	public static final String TO_PHONE_NUMBER = "+19378844148"; //test number where we receive SMS

	// user: erwtest2@gmail.com / whKhWQ:"_k7f6kXN > trial number: +16204728435
//	public static final String ACCOUNT_SID = "ACadada413930315da63ae2c81242a646d";
//	public static final String AUTH_TOKEN = "705610168b0b35c7f7833a10b68d9c70";
//	public static final String TO_PHONE_NUMBER = "+16204728435";
	

	public static void main(String[] args) {
		
		//SMSReader sms;

		// load driver
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//sms = new SMSReader();
        //sms.deleteMessages(); //clean all the existing messages if any

		driver.get("https://www.amazon.com");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("a#nav-link-accountList>span>span")).click();
	
		driver.findElement(By.xpath("//a[@id='createAccountSubmit']")).click();
		//System.out.println("createAccountSubmit");
		
		driver.findElement(By.id("ap_customer_name")).sendKeys("OTPTest1");
		//System.out.println("enter Your name");
	
		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("9378844148");
		System.out.println("enter Mobile number or email");
	
		
		driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("OTPTest123!");
		System.out.println("enter Password");
		
		driver.findElement(By.xpath("//input[@id='ap_password_check']")).sendKeys("OTPTest123!");
		System.out.println("enter Re-enter password");
		
		
		driver.findElement(By.id("continue")).click();

		
		// get the OTP using Twilio APIs:
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
		String smsBody = getMessage();
		System.out.println(smsBody);
		
		String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
		System.out.println(OTPNumber);
		
		//driver.findElement(By.id("auth-pv-enter-code")).sendKeys(OTPNumber);

		driver.close(); //closes the browser
		
	}
	
	// https://www.vinsguru.com/selenium-webdriver-multi-factor-authentication-sms/
	public void SMSReader(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
	

	public static String getMessage() {
		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals(TO_PHONE_NUMBER)).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException::new);
	}
	
	//deletes all the messages
	// https://www.vinsguru.com/selenium-webdriver-multi-factor-authentication-sms/
//    public void deleteMessages(){
//        getMessages()
//                .filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
//                .filter(m -> m.getTo().equals(TO_PHONE_NUMBER))
//                .map(Message::getSid)
//                .map(sid -> Message.deleter(ACCOUNT_SID, sid))
//                .forEach(MessageDeleter::delete);
//
//    }
	
	private static Stream<Message> getMessages() {
		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
		return StreamSupport.stream(messages.spliterator(), false);
	}
	
}
