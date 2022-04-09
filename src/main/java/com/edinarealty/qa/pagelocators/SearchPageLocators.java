package com.edinarealty.qa.pagelocators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.edinarealty.qa.util.ExcelMethods;
import com.edinarealty.qa.util.GeneralMethods;
import com.edinarealty.qa.util.ReportLoggerMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class SearchPageLocators {
	
	//Initialize Variable(s)
	protected EventFiringWebDriver eDriver;
	protected ExtentTest reportLogger;
	protected ReportLoggerMethods reportLoggerMethods;
	protected GeneralMethods genMethods;
	protected ExcelMethods excelMethods;
	
	//Constructor
	public SearchPageLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger, ExcelMethods excelMethods) {
		this.eDriver = eDriver;
		this.reportLogger = reportLogger;
		this.excelMethods = excelMethods;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	//Main Page
	@FindBy(xpath="//a[contains(text(), 'Find a home')]")
	protected WebElement searchButton;
	
	//Search Page Fields
	@FindBy(id="multiSuggester")
	protected WebElement searchCityField;
	
	@FindBy(xpath="//div[@class='select-dropdown']")
	protected List<WebElement> searchCriteriaDropDowns;
		//Status Drop-Down Options
		@FindBy(id="sts_sale_main")
		protected WebElement forSaleOption;
		
		@FindBy(id="sts_con_main")
		protected WebElement activeContingentOption;
		
		@FindBy(id="sts_pend_main")
		protected WebElement pendingOption;
		
		@FindBy(id="sts_sold_main")
		protected WebElement soldOption;
		
		@FindBy(id="sts_soon_main")
		protected WebElement comingSoonOption;
		
		//Bedroom Count Drop-Down Options
		@FindBy(xpath="//p[contains(text(), '2+ beds')]")
		protected WebElement twoBedroomOption;
		
		public void selectBedroom(String bedroomCount) {
			eDriver.findElement(By.xpath("//p[contains(text(), '" + bedroomCount + "+ beds')]")).click();
		}
		
		//Bathroom Count Drop-Down Options
		@FindBy(xpath="//p[contains(text(), '2+ baths')]")
		protected WebElement twoBathroomOption;
		
		public void selectBathroom(String bathroomCount) {
			eDriver.findElement(By.xpath("//p[contains(text(), '" + bathroomCount + "+ baths')]")).click();
		}
	
	@FindBy(xpath="//div[@class='select-dropdown price-range-input']")
	protected WebElement priceRangeDropDown;
		//Status Drop-Down Options
		@FindBy(id="minPrice_main")
		protected WebElement minPriceOption;
		
		@FindBy(id="maxPrice_main")
		protected WebElement maxPriceOption;
	
	@FindBy(xpath="//a[contains(text(), 'Save search')]")
	protected WebElement saveSearchButton;
	
	//Save Search Pop-up
	@FindBy(id="searchName")
	protected WebElement saveSearchNameField;
	
	@FindBy(xpath="//button[contains(text(), 'Save')]")
	protected WebElement confirmSaveSearchButton;
	
	//Save property
	@FindBy(xpath="//span[@ng-if='!item.document.auctionYn']")
	protected List<WebElement> propertyCostList;
	
	@FindBy(xpath="//p[@class='mls-number']//span")
	protected List<WebElement> propertyMLSList;
	
	@FindBy(xpath="//span[@itemprop='streetAddress']")
	protected List<WebElement> propertyStreetAddressList;
	
	@FindBy(xpath="//span[@itemprop='addressLocality']")
	protected List<WebElement> propertyCityList;
	
	@FindBy(xpath="//span[@itemprop='addressRegion']")
	protected List<WebElement> propertyStateList;
	
	@FindBy(xpath="//span[@itemprop='postalCode']")
	protected List<WebElement> propertyZipCodeList;
	
	@FindBy(xpath="//a[@class='saved-property']")
	protected List<WebElement> savePropertyList;
	
	//Saved Info
	@FindBy(xpath="//div[@ng-repeat='search in savedSearch.savedSearches']//h4")
	protected List<WebElement> savedSearchList;
	
	@FindBy(xpath="//a[contains(text(), 'Delete')]")
	protected List<WebElement> savedSearchesDeleteButton;
	
	@FindBy(xpath="//a[@class='saved-property']")
	protected List<WebElement> savedPropertiesFavouriteButton;
	
	@FindBy(xpath="//button[@ng-click='unfavoriteProperty()']")
	protected WebElement unfavouritePopupButton;
	
	@FindBy(xpath="//span[@ng-if='!item.document.auctionYn']")
	protected List<WebElement> savedPropertyCostList;
	
	@FindBy(xpath="//p[@class='mls-number']//span")
	protected List<WebElement> savedPropertyMLSList;
	
	@FindBy(xpath="//span[@class='address-line']")
	protected List<WebElement> savedPropertyAddressList;
	
	@FindBy(xpath="//span[@itemprop='addressLocality']")
	protected List<WebElement> savedPropertyCityList;
	
	@FindBy(xpath="//span[@itemprop='addressRegion']")
	protected List<WebElement> savedPropertyStateList;
	
	@FindBy(xpath="//span[@itemprop='postalCode']")
	protected List<WebElement> savedPropertyZipCodeList;
	
	//Account Drop-Down
	@FindBy(xpath="//span[@class='nameInitals']")
	protected WebElement userAccountDropDown;
	
//	@FindBy(xpath="//a[contains(text(), 'Favorite properties')]")
	@FindBy(xpath="//a[@href='/account/savedlistinglist']")
	protected WebElement favoritePropertiesButton;
	
	@FindBy(xpath="//a[contains(text(), 'Saved searches')]")
	protected WebElement savedSearchesButton;
}