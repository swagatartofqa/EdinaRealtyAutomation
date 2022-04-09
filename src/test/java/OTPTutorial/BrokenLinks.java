package OTPTutorial;

import org.openqa.selenium.By;		
import org.testng.annotations.Test;

import com.edinarealty.qa.base.TestBase;
import com.edinarealty.qa.util.ExtentFactory;

import java.util.List;		
import org.openqa.selenium.*;		

public class BrokenLinks extends TestBase {				
    
	@Test()
    public static void main() {
		
        String baseUrl = "https://staging.edinarealtyv2.com/";					
//        System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");					
//        WebDriver driver = new ChromeDriver();					
        		
        String underConsTitle = "Under Construction: Mercury Tours";					
//			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);					

//		driver.get(baseUrl);
		eDriver.get(baseUrl);
        List<WebElement> linkElements = eDriver.findElements(By.tagName("a"));
        String[] linkTexts = new String[linkElements.size()];							
			int	i = 0;					

			//extract the link texts of each link element		
			for (WebElement e : linkElements) {							
			linkTexts[i] = e.getText();							
			i++;			
        }		

			//test each link		
			for (String t : linkTexts) {							
				eDriver.findElement(By.linkText(t)).click();					
			if (eDriver.getTitle().equals(underConsTitle)) {							
                System.out.println("\"" + t + "\""								
                        + " is under construction.");			
            } else {			
                System.out.println("\"" + t + "\""								
                        + " is working.");			
            }		
			eDriver.navigate().back();			
        }		
//			driver.quit();			
    }		
}