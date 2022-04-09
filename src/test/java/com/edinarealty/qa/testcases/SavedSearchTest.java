package com.edinarealty.qa.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.edinarealty.qa.base.TestBase;
import com.edinarealty.qa.pagemethods.AccountPageMethods;
import com.edinarealty.qa.pagemethods.LoginPageMethods;
import com.edinarealty.qa.pagemethods.SearchPageMethods;
import com.edinarealty.qa.util.ExcelMethods;
import com.edinarealty.qa.util.ExtentFactory;
import com.edinarealty.qa.util.GeneralMethods;
import com.relevantcodes.extentreports.LogStatus;

public class SavedSearchTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public SavedSearchTest() {
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
		accountPageMethods = new AccountPageMethods(eDriver, reportLogger);
	}
	
	//Setup variable(s) and other info for the class
	@BeforeClass
	@Parameters({"dataTable"})
	public void beforeClass(String excelPath) {
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setDataTablePath(excelPath);
		excelMethods.setSheetName("Saved Searches");
		column = 11;
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void savedSearchTest(String active, String reportTitle, String website, String citySearchCriteria, String minPriceSearchCriteria, String maxPriceSearchCriteria, String bedroomCountSearchCriteria, String bathroomCountSearchCriteria, String savedSearchName, String expectedSavedSearchName, String finalResult, String dataRow) {
		System.out.println("@Test - savedSearchTest()");
		
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
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);
			
			//Delete saved Searches
			searchPageMethods.deleteSavedSearches();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);
			
			//Navigate to the website
//			loginPageMethods.navigateToWebsite(website);
			
			//Perform & Save a Search
			searchPageMethods.performSearch(citySearchCriteria, minPriceSearchCriteria, maxPriceSearchCriteria, bedroomCountSearchCriteria, bathroomCountSearchCriteria, savedSearchName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);
			
			//Go to the account's 'Saved Search' & verify info
			searchPageMethods.verifySavedSearchInfo(checkpoint, expectedSavedSearchName);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}