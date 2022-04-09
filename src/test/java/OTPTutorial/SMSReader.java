package OTPTutorial;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

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

 
public class SMSReader {

	// user: erwtest1@gmail.com / whKhWQ:"_k7f6kXN > trial number: +19378844148
	public static final String ACCOUNT_SID = "AC8804e95011bea8ee22be6a95b61568e9";
	public static final String AUTH_TOKEN = "db6c30635b4d8025c5efa9d38552c981";
	public static final String TO_PHONE_NUMBER = "+19378844148"; //test number where we receive SMS
	

    public SMSReader(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public String getMessage(){
        return getMessages()
                    .filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
                    .filter(m -> m.getTo().equals(TO_PHONE_NUMBER))
                    .map(Message::getBody)
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);
    }
    //deletes all the messages
    public void deleteMessages(){
        getMessages()
                .filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
                .filter(m -> m.getTo().equals(TO_PHONE_NUMBER))
                .map(Message::getSid)
                .map(sid -> Message.deleter(ACCOUNT_SID, sid))
                .forEach(MessageDeleter::delete);

    }
    
    private Stream<Message> getMessages(){
        ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
        return StreamSupport.stream(messages.spliterator(), false);
    }

}