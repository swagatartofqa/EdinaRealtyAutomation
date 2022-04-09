//package OTPTutorial;
//
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//
//// How to use Automation Utilities in the project
//// https://www.youtube.com/watch?v=CfPhlhovzL4
//
//// How to get OTP from Email
//// https://www.youtube.com/watch?v=lQ1Qqp4_Q5w
//
//// Automation Utilities
//// https://dotclicklabs.blogspot.com/p/guide-lines-to-use-automation-utilities.html
//
//
////JavaMail API Tutorial
////https://www.tutorialspoint.com/javamail_api/javamail_api_checking_emails.htm
//
//
//public class GetOTP_From_Email {
//
//	public static void main(String[] args)  {
//
//		// load driver
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//
//		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//
//		// stage
//		driver.get("https://staging.edinarealtyv2.com/");
//
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//
//		// Enter Email
//		// Gmail account: erwtest2@gmail.com / Test#123$
//		driver.findElement(id="signinAjax");
//
//
//		Actions a = new Actions (driver);
//		a.sendKeys("erwtest2@gmail.com").build().perform();
//
//		// Click on Next
//		driver.findElement(By.xpath("//input[@type='submit']")).click();
//
//
//
//	}
//
//}
