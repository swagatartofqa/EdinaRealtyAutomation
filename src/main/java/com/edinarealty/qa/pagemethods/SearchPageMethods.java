package com.edinarealty.qa.pagemethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.edinarealty.qa.pagelocators.SearchPageLocators;
import com.edinarealty.qa.util.ExcelMethods;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;

public class SearchPageMethods extends SearchPageLocators {

	public SearchPageMethods(EventFiringWebDriver eDriver, ExtentTest reportLogger, ExcelMethods excelMethods) {
		super(eDriver, reportLogger, excelMethods);
	}

	public void performSearch(String citySearchCriteria, String minPrice, String maxPrice, String bedroomCountSearchCriteria, String bathroomCountSearchCriteria, String savedSearchName) {
		//Output to the system and report
		System.out.println("Performing a search for '" + savedSearchName + "'");
		reportLogger.log(LogStatus.INFO, "Performing a search for '" + savedSearchName + "'");
		try {
			//Navigate to the 'Search Properties' page
			searchButton.click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(2000);

			//Enter the city search criteria
			searchCityField.sendKeys(citySearchCriteria);

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);

			//Select Status Drop-Down Options
			searchCriteriaDropDowns.get(0).click();


			if (!forSaleOption.isSelected()) {
				forSaleOption.click();
			}

			if (activeContingentOption.isSelected()) {
				activeContingentOption.click();
			}

			if (pendingOption.isSelected()) {
				pendingOption.click();
			}

			if (soldOption.isSelected()) {
				soldOption.click();
			}

			if (comingSoonOption.isSelected()) {
				comingSoonOption.click();
			}

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);

			//Select the price range
			priceRangeDropDown.click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);

			//Remove any existing info
			for (int i = 0; i < 9; i++) {
				minPriceOption.sendKeys(Keys.BACK_SPACE);
			}

			//Enter the min price
			minPriceOption.sendKeys(minPrice);

			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);

			//Remove any existing info
			for (int i = 0; i < 9; i++) {
				maxPriceOption.sendKeys(Keys.BACK_SPACE);
			}

			//Enter the max price
			maxPriceOption.sendKeys(maxPrice);

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);

			//Select the amount of bedrooms
			searchCriteriaDropDowns.get(1).click();
			genMethods.waitForMilliseconds(1000);
			selectBedroom(bedroomCountSearchCriteria);

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);

			//Select the amount of bathrooms
			searchCriteriaDropDowns.get(2).click();
			genMethods.waitForMilliseconds(1000);
			selectBathroom(bathroomCountSearchCriteria);

			//Pause the script for a bit
			genMethods.waitForMilliseconds(6000);

			//Click the 'Save' button
			saveSearchButton.click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(2000);

			//Enter the saved search name
			saveSearchNameField.sendKeys(savedSearchName);
			genMethods.waitForMilliseconds(1000);
			//Click 'Save'
			confirmSaveSearchButton.click();
		} catch (Exception e) {
			reportLoggerMethods.reportFailedCheckpoint("Performing a Search and saving said Searh failed", "performSearchAndSave");
		}
	}

	public void performSearch(String citySearchCriteria, String minPrice, String maxPrice, String bedroomCountSearchCriteria, String bathroomCountSearchCriteria) {
		//Output to the system and report
		System.out.println("Performing a search for '" + citySearchCriteria + "'");
		reportLogger.log(LogStatus.INFO, "Performing a search for '" + citySearchCriteria + "'");
		try {
			//Navigate to the 'Search Properties' page
			searchButton.click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(2000);
			//Pause the script for a bit

			//Enter the city search criteria
			searchCityField.sendKeys(citySearchCriteria);

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);

			//Select Status Drop-Down Options
			searchCriteriaDropDowns.get(0).click();

			if (!forSaleOption.isSelected()) {
				forSaleOption.click();
			}

			if (activeContingentOption.isSelected()) {
				activeContingentOption.click();
			}

			if (pendingOption.isSelected()) {
				pendingOption.click();
			}

			if (soldOption.isSelected()) {
				soldOption.click();
			}

			if (comingSoonOption.isSelected()) {
				comingSoonOption.click();
			}

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);

			//Select the price range
			priceRangeDropDown.click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);

			//Remove any existing info
			for (int i = 0; i < 9; i++) {
				minPriceOption.sendKeys(Keys.BACK_SPACE);
			}

			//Enter the min price
			minPriceOption.sendKeys(minPrice);

			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);

			//Remove any existing info
			for (int i = 0; i < 9; i++) {
				maxPriceOption.sendKeys(Keys.BACK_SPACE);
			}

			//Enter the max price
			maxPriceOption.sendKeys(maxPrice);

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);

			//Select the amount of bedrooms
			searchCriteriaDropDowns.get(1).click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);

			//Select the number of bedrooms
			selectBedroom(bedroomCountSearchCriteria);

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);

			//Select the amount of bathrooms
			searchCriteriaDropDowns.get(2).click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);

			//Select the amount of bathrooms
			selectBathroom(bathroomCountSearchCriteria);

			//Pause the script for a bit
			genMethods.waitForMilliseconds(6000);
		} catch (Exception e) {
			reportLoggerMethods.reportFailedCheckpoint("Performing a Search failed", "performSearchAndSave");
		}
	}

	public void saveProperties(String dataRow) {
		//Output to the system and report
		System.out.println("Saving select properties");
		reportLogger.log(LogStatus.INFO, "Saving select properties");
		try {
			//Initialize Variable(s)
			String fullAddress = "";

			//Pause the script for a bit
			genMethods.waitForMilliseconds(5000);

			//Save select properties
			try {
				for (int i = 1; i < 4; i++) {
					//Save the desired Property
					savePropertyList.get(i).click();

					//Output the Property Cost to the Excel DataTable
					excelMethods.setDataTableCell(propertyCostList.get(i).getText(), Integer.parseInt(dataRow), (6+i*3));

					//Output the Property MLS # to the Excel DataTable
					excelMethods.setDataTableCell(propertyMLSList.get(i).getText(), Integer.parseInt(dataRow), (7+i*3));

					//Output the Full Address to the Excel DataTable
					fullAddress = propertyStreetAddressList.get(i).getText() + " " + propertyCityList.get(i).getText() + ", " + propertyStateList.get(i).getText() + ", " + propertyZipCodeList.get(i).getText();
					excelMethods.setDataTableCell(fullAddress, Integer.parseInt(dataRow), (8+i*3));

					//Pause the script for a bit
					genMethods.waitForMilliseconds(1000);
				}
			} catch (Exception e) {
				reportLoggerMethods.reportFailedCheckpoint("Failure: Unable to save a property", "saveProperties");
			}
		} catch (Exception e) {
			reportLoggerMethods.reportFailedCheckpoint("Saving Properties failed", "saveProperties");
		}
	}

	public void saveProperties2(String dataRow) {
		//Output to the system and report
		System.out.println("Saving select properties");
		reportLogger.log(LogStatus.INFO, "Saving select properties");
		try {
			//Initialize Variable(s)
			String fullAddress = "";

			//Pause the script for a bit
			genMethods.waitForMilliseconds(5000);

			//Save select properties
			try {
				for (int i = 1; i < 4; i++) {
					//Save the desired Property
					savePropertyList.get(i).click();

					//Output the Property Cost to the Excel DataTable
					excelMethods.setDataTableCell(propertyCostList.get(i).getText(), Integer.parseInt(dataRow), (6+i*4));

					//Output the Property MLS # to the Excel DataTable
					excelMethods.setDataTableCell(propertyMLSList.get(i).getText(), Integer.parseInt(dataRow), (7+i*4));

					//Output the Full Address to the Excel DataTable
					fullAddress = propertyStreetAddressList.get(i).getText() + " " + propertyCityList.get(i).getText() + ", " + propertyStateList.get(i).getText() + ", " + propertyZipCodeList.get(i).getText();
					excelMethods.setDataTableCell(fullAddress, Integer.parseInt(dataRow), (8+i*4));

					//Pause the script for a bit
					genMethods.waitForMilliseconds(1000);
				}
			} catch (Exception e) {
				reportLoggerMethods.reportFailedCheckpoint("Failure: Unable to save a property", "saveProperties");
			}
		} catch (Exception e) {
			reportLoggerMethods.reportFailedCheckpoint("Saving Properties failed", "saveProperties2");
		}
	}

	public void deleteSavedProperties() {
		//Output to the system and report
		System.out.println("Deleting any previous Saved Properties, if any exist");
		reportLogger.log(LogStatus.INFO, "Deleting any previous Saved Properties, if any exist");
		try {
			//Navigate to the 'Search Properties' page
			searchButton.click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(2000);

			//Hover over the account drop-down list
			Actions actions = new Actions(eDriver);
			actions.moveToElement(userAccountDropDown).perform();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);

			//Click the 'Saved Properties' button
			WebDriverWait webDriverWait = new WebDriverWait(eDriver, 30);
			webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@href='/account/savedlistinglist']")));
			actions.click(favoritePropertiesButton).click().perform();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);
			try {
				//
				webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@class='saved-property']")));

				//Loop through the list of all Saved Properties
				int savedPropertiesCount = savedPropertiesFavouriteButton.size();


				//Loop through & delete all saved properties
				for (int i = 0; i < savedPropertiesCount; i++) {
					//Select the saved Properties' (un)favourite button
					savedPropertiesFavouriteButton.get(savedPropertiesCount-(1+i)).click();

					//Pause the script for a bit
					genMethods.waitForMilliseconds(3000);

					//Unfavourite the selected property
					unfavouritePopupButton.click();

					//Pause the script for a bit
					genMethods.waitForMilliseconds(3000);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			reportLoggerMethods.reportFailedCheckpoint("Deleting Saved Properties failed", "deleteSavedProperties");
		}
	}

	public void verifySavedProperties(SoftAssert softAssert, int testRow) {
		//Output to the system and report
		System.out.println("Verifying that the desired Properties were saved");
		reportLogger.log(LogStatus.INFO, "Verifying that the desired Properties were saved");
		try {
			//Hover over the account drop-down list
			Actions actions = new Actions(eDriver);
			actions.moveToElement(userAccountDropDown).perform();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);

			//Click the 'Saved Searches' button
			actions.click(favoritePropertiesButton).click().perform();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);

			//Loop through the list of all Saved Properties
			for (int i = 0; i < 3; i++) {
				//Initialize Variable(s)
				String fullPropertyAddress = savedPropertyAddressList.get(2*i).getText() + " " + savedPropertyCityList.get(i).getText() + ", " + savedPropertyStateList.get(i).getText() + ", " + savedPropertyZipCodeList.get(i).getText();
				String propertyCost = excelMethods.getDataTableCell(testRow, (8+i*3));
				String propertyMLS = excelMethods.getDataTableCell(testRow, (9+i*3));
				String propertyAddress = excelMethods.getDataTableCell(testRow, (10+i*3));

				//Output to the system and report
				System.out.println("Verifying that the '" + propertyMLS + "' Property were saved");
				reportLogger.log(LogStatus.INFO, "Verifying that the '" + propertyMLS + "' Property were saved");

				//Assert the status of the Saved Properties Info
				softAssert.assertEquals(savedPropertyCostList.get(i).getText(), propertyCost);
				softAssert.assertEquals(savedPropertyMLSList.get(i).getText(), propertyMLS);
				softAssert.assertEquals(fullPropertyAddress, propertyAddress);

				//Output the script results to the Extent Report
				if (savedPropertyCostList.get(i).getText().equalsIgnoreCase(propertyCost) && savedPropertyMLSList.get(i).getText().equalsIgnoreCase(propertyMLS) && fullPropertyAddress.equalsIgnoreCase(propertyAddress)) {
					reportLoggerMethods.reportSuccessfulCheckpoint("Success: The '" + propertyMLS + "' property has been saved properly");
				} else {
					reportLoggerMethods.reportFailedCheckpoint("Failure: The '" + propertyMLS + "' property has not been saved as expected", "verifySavedProperties");
				}
			}
		} catch (Exception e) {
			reportLoggerMethods.reportFailedCheckpoint("Verifying the Saved Properties failed", "verifySavedProperties");
		}
	}

	public void verifySavedProperties2(SoftAssert softAssert, int testRow) {
		//Output to the system and report
		System.out.println("Verifying that the desired Properties were saved");
		reportLogger.log(LogStatus.INFO, "Verifying that the desired Properties were saved");
		try {
			//Hover over the account drop-down list
			Actions actions = new Actions(eDriver);
			actions.moveToElement(userAccountDropDown).perform();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);

			//Click the 'Saved Searches' button
			actions.click(favoritePropertiesButton).click().perform();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);

			//Loop through the list of all Saved Properties
			for (int i = 0; i < 3; i++) {
				//Initialize Variable(s)
				String savedPropertyListingStr = excelMethods.getDataTableCell(testRow, (8+i*4));
				int savedPropertyListing = Integer.parseInt(savedPropertyListingStr);

				String fullPropertyAddress = savedPropertyAddressList.get(2*savedPropertyListing).getText() + " " + savedPropertyCityList.get(savedPropertyListing).getText() + ", " + savedPropertyStateList.get(savedPropertyListing).getText() + ", " + savedPropertyZipCodeList.get(savedPropertyListing).getText();
				String propertyCost = excelMethods.getDataTableCell(testRow, (9+i*4));
				String propertyMLS = excelMethods.getDataTableCell(testRow, (10+i*4));
				String propertyAddress = excelMethods.getDataTableCell(testRow, (11+i*4));

				//Output to the system and report
				System.out.println("Verifying that the '" + propertyMLS + "' Property were saved");
				reportLogger.log(LogStatus.INFO, "Verifying that the '" + propertyMLS + "' Property were saved");

				//Assert the status of the Saved Properties Info
				softAssert.assertEquals(savedPropertyCostList.get(savedPropertyListing).getText(), propertyCost);
				softAssert.assertEquals(savedPropertyMLSList.get(savedPropertyListing).getText(), propertyMLS);
				softAssert.assertEquals(fullPropertyAddress, propertyAddress);

				//Output the script results to the Extent Report
				if (savedPropertyCostList.get(savedPropertyListing).getText().equalsIgnoreCase(propertyCost) && savedPropertyMLSList.get(savedPropertyListing).getText().equalsIgnoreCase(propertyMLS) && fullPropertyAddress.equalsIgnoreCase(propertyAddress)) {
					reportLoggerMethods.reportSuccessfulCheckpoint("Success: Property '" + propertyMLS + "' has been saved properly");
				} else {
					reportLoggerMethods.reportFailedCheckpoint("Failure: Property '" + propertyMLS + "' has not been saved as expected", "verifySavedProperties");
				}
			}
		} catch (Exception e) {
			reportLoggerMethods.reportFailedCheckpoint("Verifying the Saved Properties failed", "verifySavedProperties2");
		}
	}

	public void deleteSavedSearches() {
		//Output to the system and report
		System.out.println("Deleting previous Saved Searches, if any exist");
		reportLogger.log(LogStatus.INFO, "Deleting previous Saved Searches, if any exist");
		try {
			//Navigate to the 'Search Properties' page
			searchButton.click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(2000);

			//Hover over the account drop-down list
			Actions actions = new Actions(eDriver);
			actions.moveToElement(userAccountDropDown).perform();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);

			//Click the 'Saved Searches' button
			WebDriverWait webDriverWait = new WebDriverWait(eDriver, 30);
			webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), 'Saved searches')]")));
			actions.click(savedSearchesButton).click().perform();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(8000);

			//Loop through & delete all saved searches
			for (int i = 0; i < savedSearchesDeleteButton.size()+1; i++) {
				try {
					//Select the saved Properties' (un)favourite button
					savedSearchesDeleteButton.get(savedSearchesDeleteButton.size()-(1+i)).click();

					//Pause the script for a bit
					genMethods.waitForMilliseconds(3000);

					//Unfavourite the selected property
					unfavouritePopupButton.click();

					//Pause the script for a bit
					genMethods.waitForMilliseconds(3000);
				} catch (Exception e) {
					return;
				}
			}
		} catch (Exception e) {
			reportLoggerMethods.reportFailedCheckpoint("Deleting Saved Searches Failed", "deleteSavedSearches");
		}
	}

	public void verifySavedSearchInfo(SoftAssert softAssert, String savedSearchName) {
		//Output to the system and report
		System.out.println("Verifying that the '" + savedSearchName + "' Search was saved");
		reportLogger.log(LogStatus.INFO, "Verifying that the '" + savedSearchName + "' Search was saved");
		try {
			//Initialize the Actions variable before selecting the 'Saved Searches' option from the account drop-down list
			Actions actions = new Actions(eDriver);

			//Hover over the account drop-down list
			actions.moveToElement(userAccountDropDown).perform();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			System.out.println("1 - " + savedSearchesButton.isDisplayed());
			//Click the 'Saved Searches' button
			actions.click(savedSearchesButton).click().perform();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);
			System.out.println("1 - " + savedSearchList.get(0).isDisplayed());
			//
			if (savedSearchList.get(0).getText().trim().equalsIgnoreCase(savedSearchName)) {
				//
				softAssert.assertEquals(savedSearchList.get(0).getText(), savedSearchName);

				//
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The '" + savedSearchName + "' search has been saved properly");
			} else {
				//
				softAssert.assertEquals(savedSearchList.get(0).getText(), savedSearchName);

				//
				reportLoggerMethods.reportFailedCheckpoint("Failure: The '" + savedSearchName + "' search has not been saved properly", "verifySavedInfo");
			}
		} catch (Exception e) {
			reportLoggerMethods.reportFailedCheckpoint("Verifying Saved Searches Failed", "verifySavedSearchInfo");
		}
	}





	public void verifySaleHistoryAndTaxSummaryIsDisplayed(String propState , String propValue) {
		System.out.println("Performing a search for and Validating the Sales History And Tax Summary");
		reportLogger.log(LogStatus.INFO, "Performing a search for and Validating the Sales History And Tax Summary");
		try{
			searchButton.click();
			genMethods.waitForMilliseconds(6000);
			//Enter the city search criteria
			searchCityField.sendKeys(propState);
			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);

			search_button.click();
			//Pause the script for a bit
			genMethods.waitForMilliseconds(12000);
			((JavascriptExecutor)eDriver).executeScript("arguments[0].scrollIntoView(true);", eDriver.findElement(By.xpath("//span[contains(text(),'"+propValue+"')]")));
			genMethods.waitForMilliseconds(2000);
			eDriver.findElement(By.xpath("//span[contains(text(),'"+propValue+"')]")).click();
			genMethods.waitForMilliseconds(3000);
			reportLogger.log(LogStatus.INFO, "Scrolled to the Sales History And Tax Summary");

			((JavascriptExecutor)eDriver).executeScript("arguments[0].scrollIntoView(true);", salesHistoryAndTaxSummaryPlusIcon);
			genMethods.waitForMilliseconds(3000);
			JavascriptExecutor executor = (JavascriptExecutor) eDriver;
			executor.executeScript("arguments[0].click();", salesHistoryAndTaxSummaryPlusIcon);

			genMethods.waitForMilliseconds(5000);
			if(taxSummaryTable.isDisplayed()) {
				if(taxSummaryTableHeader_TaxYear.isDisplayed()){
					reportLogger.log(LogStatus.PASS,"Tax Summary Table Header Tax Year is Displayed");
				}
				if(taxSummaryTableHeader_EMA.isDisplayed()){
					reportLogger.log(LogStatus.PASS,"Tax Summary Table Header Estimated Market Value is Displayed");
				}
				if(taxSummaryTableHeader_TaxableMarketValue.isDisplayed()){
					reportLogger.log(LogStatus.PASS,"Tax Summary Table Header Taxable Market Value is Displayed");
				}
				if(taxSummaryTableHeader_TotalTax.isDisplayed()){
					reportLogger.log(LogStatus.PASS,"Tax Summary Table Header Total Tax is Displayed");
				}
				if(taxSummaryTable_BodyValue.isDisplayed()){
					reportLogger.log(LogStatus.PASS,"Tax Summary Table Body values is Displayed");
				}
				reportLogger.log(LogStatus.PASS,"Tax Summary Table is Displayed");
			}

			((JavascriptExecutor)eDriver).executeScript("arguments[0].scrollIntoView(true);", userAccountDropDown);


		}catch (Exception e){e.printStackTrace();}

	}





}