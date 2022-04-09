package com.edinarealty.qa.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.edinarealty.qa.base.TestBase;
import com.edinarealty.qa.pagemethods.AccountPageMethods;
import com.edinarealty.qa.pagemethods.LoginPageMethods;
import com.edinarealty.qa.util.ExcelMethods;
import com.edinarealty.qa.util.ExtentFactory;
import com.edinarealty.qa.util.GeneralMethods;
import com.edinarealty.qa.util.OTPMethods;
import com.relevantcodes.extentreports.LogStatus;

public class MySettingsTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public MySettingsTest() {
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
		accountPageMethods = new AccountPageMethods(eDriver, reportLogger);
	}
	
	//Setup variable(s) and other info for the class
	@BeforeClass
	@Parameters({"dataTable"})
	public void beforeClass(String excelPath) {
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		otpMethods = new OTPMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setDataTablePath(excelPath);
		excelMethods.setSheetName("My Settings");
		column = 10;
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void topRightSearchBarTest(String active, String reportTitle, String website, String firstName, String lastName, String email, String expectedFirstName, String expectedLastName, String expectedEmail, String finalResult, String dataRow) {
		System.out.println("@Test - topRightSearchBarTest()");
		
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
			
			//
			accountPageMethods.updatePhoneNumber("3332221111");
			
//			accountPageMethods.updatePhoneNumberPart2("Cynthia.Astralis@gmail.com", "Akatsukimember1!", "9402835138");
			accountPageMethods.updatePhoneNumberPart2("Cynthia.Astralis@gmail.com", "Akatsukimember1!", "9378844148");
			
			//Pause thes cript for a short bit
			genMethods.waitForMilliseconds(15000);
			
			//Retrieve the OTP value
			String OTPNumber = otpMethods.outputOTPNumber();
			
			//Confirm the OTP value
			accountPageMethods.confirmOTPCode(OTPNumber);
			
			//Pause thes cript for a short bit
			genMethods.waitForMilliseconds(2000);
			
			//
			accountPageMethods.updateName(firstName, lastName);
			
			accountPageMethods.verifySavedInfoViaRefresh(checkpoint, expectedFirstName, expectedLastName);
			accountPageMethods.verifySavedInfo(checkpoint, expectedFirstName, expectedLastName);
			
			//expectedEmail
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}