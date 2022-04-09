package com.edinarealty.qa.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.edinarealty.qa.base.TestBase;
import com.edinarealty.qa.pagemethods.LoginPageMethods;
import com.edinarealty.qa.pagemethods.SearchPageMethods;
import com.edinarealty.qa.util.ExcelMethods;
import com.edinarealty.qa.util.ExtentFactory;
import com.edinarealty.qa.util.GeneralMethods;
import com.relevantcodes.extentreports.LogStatus;

public class SavedPropertiesTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public SavedPropertiesTest() {
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
		searchPageMethods = new SearchPageMethods(eDriver, reportLogger, excelMethods);
	}
	
	//Setup variable(s) and other info for the class
	@BeforeClass
	@Parameters({"dataTable"})
	public void beforeClass(String excelPath) {
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setDataTablePath(excelPath);
		excelMethods.setSheetName("Saved Properties");
		column = 21;
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void savedPropertiesTest(String active, String reportTitle, String website, String citySearchCriteria, String minPriceSearchCriteria, String maxPriceSearchCriteria, String bedroomCountSearchCriteria, String bathroomCountSearchCriteria, String savedPropertyListing1, String propertyCost1, String propertyMLS1, String propertyAddress1, String savedPropertyListing2, String propertyCost2, String propertyMLS2, String propertyAddress2, String savedPropertyListing3, String propertyCost3, String propertyMLS3, String propertyAddress3, String finalResult, String dataRow) {
		System.out.println("@Test - savedPropertiesTest()");
		
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
			
			//Navigate to the website
//			loginPageMethods.navigateToWebsite(website);
			
			//Delete saved Properties
			searchPageMethods.deleteSavedProperties();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);
			
			//Navigate to the website
//			loginPageMethods.navigateToWebsite(website);
			
			//Perform a search
			searchPageMethods.performSearch(citySearchCriteria, minPriceSearchCriteria, maxPriceSearchCriteria, bedroomCountSearchCriteria, bathroomCountSearchCriteria);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);
			
			//Save the first three properties
			searchPageMethods.saveProperties2(dataRow);
			
			//Go to the account's 'Saved Properties' to verify the saved properties' info
			searchPageMethods.verifySavedProperties2(checkpoint, iteration);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}