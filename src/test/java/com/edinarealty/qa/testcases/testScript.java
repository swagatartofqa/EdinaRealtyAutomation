package com.edinarealty.qa.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.edinarealty.qa.base.TestBase;
import com.edinarealty.qa.pagemethods.LoginPageMethods;
import com.edinarealty.qa.util.ExcelMethods;
import com.edinarealty.qa.util.ExtentFactory;
import com.edinarealty.qa.util.GeneralMethods;
import com.relevantcodes.extentreports.LogStatus;

public class testScript extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public testScript() {
		super();
	}
	
	public void performSetup(String reportTitle) {
		//Setup the Report
		report = ExtentFactory.getInstance(activeBrowser, activeBrowserStack);
		reportLogger = report.startTest(reportTitle);
		
		//Initialize PageFactories
		System.out.println("Initializing the script's PageFactories");
		reportLogger.log(LogStatus.INFO, "Initializing the script's PageFactories");
		
		//Setup PageFactory
		loginPageMethods = new LoginPageMethods(eDriver, reportLogger);
	}
	
	//Setup variable(s) and other info for the class
	@BeforeClass
	@Parameters({"dataTable"})
	public void beforeClass(String excelPath) {
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setDataTablePath(excelPath);
		excelMethods.setSheetName("Login2");
		column = 9;
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void loginTest(String active, String reportTitle, String website, String userid, String password, String expectedSuccessOrFailure, String expectedLoginName, String signout, String finalResult, String dataRow) {
		System.out.println("@Test - loginTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Remove the output values from a previous script run
		if (iteration > 1) {
			excelMethods.setDataTableCell("", iteration, column);
		}
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle);
			try {
			//delete browser cookies
			eDriver.manage().deleteAllCookies();
			
			String baseUrl = "http://demo.guru99.com/test/newtours/";
			String underConsTitle = "Under Construction: Mercury Tours";
			
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
					System.out.println("\"" + t + "\"" + " is under construction.");			
				} else {			
					System.out.println("\"" + t + "\"" + " is working.");
				}
				
//				if (eDriver.getCurrentUrl().equalsIgnoreCase("")) {
//					System.out.println(eDriver.getCurrentUrl());
//					System.out.println("hi");
//				} else {
//					System.out.println(eDriver.getCurrentUrl());
//					System.out.println("bye");
//					eDriver.navigate().back();
//				}
				eDriver.navigate().back();
				if (eDriver.getCurrentUrl().equalsIgnoreCase("data:,")) {
					eDriver.navigate().forward();
				}
				
			}
			} catch (Exception e) {
//				System.out.println(e);
			}
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}