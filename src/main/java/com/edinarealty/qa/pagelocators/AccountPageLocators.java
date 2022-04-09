package com.edinarealty.qa.pagelocators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.edinarealty.qa.util.GeneralMethods;
import com.edinarealty.qa.util.ReportLoggerMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class AccountPageLocators {

	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;

	//Constructor
	public AccountPageLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);

		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}

	//Logout Locators
	@FindBy(id="searchBtn")
	protected WebElement searchButton;

	@FindBy(xpath="//a[contains(text(), 'Find a home')]")
	protected WebElement findAHomeButton;

	@FindBy(xpath="//span[@class='nameInitals']")
	protected WebElement userAccountDropDown;

	@FindBy(xpath="//a[contains(text(), 'Settings')]")
	protected WebElement accountSettingsButton;

	@FindBy(xpath="//a[contains(text(), 'Favorite properties')]")
	protected WebElement favoritePropertiesButton;

	@FindBy(xpath="//a[contains(text(), 'Saved searches')]")
	protected WebElement savedSearchesButton;

	//Update Account Info
	@FindBy(id="firstName")
	protected WebElement accountFirstNameField;

	@FindBy(id="lastName")
	protected WebElement accountLastNameField;

	@FindBy(xpath="//div[@class='updateProName show']")
	protected WebElement updatedNameLabel;

	@FindBy(id="updateProfileName")
	protected WebElement updateAccountNameButton;

	//Phones
	@FindBy(id="phone")
//	protected WebElement accountPhoneField;
	protected List<WebElement> accountPhoneField;

	@FindBy(xpath="//button[contains(text(), 'Update phone number')]")
	protected WebElement updateAccountPhoneButton;

	@FindBy(xpath="//a[@class='settingNameLink']")
	protected WebElement updateAccountPhoneNumberButton;

	@FindBy(xpath="//button[@class='accountButton']")
	protected WebElement signinViaEmailButton;

	@FindBy(id="signInName")
	protected WebElement signinEmailAddressField;

	@FindBy(id="password")
	protected WebElement signinPasswordField;

	@FindBy(id="continue")
	protected WebElement signinConfirmButton;

	@FindBy(id="number")
	protected WebElement enterPhonNumberField;

	@FindBy(id="sendCode")
	protected WebElement sendOTPCodeButton;

	@FindBy(id="verificationCode")
	protected WebElement verifyOTPCodeField;

	@FindBy(id="verifyCode")
	protected WebElement verifyOTPCodeButton;

	@FindBy(id="mfaUnknownDeviceConsentChoice_AgreeToMfaUnknownDeviceYes")
	protected WebElement doNotVerifyMyDeviceCheckbox;

	//email
	@FindBy(id="emailAddress")
	protected WebElement accountEmailField;

	@FindBy(xpath="//div[@class='updateProName show']")
	protected WebElement updatedEmailLabel;

//	WebElement fName=driver.findElement(By.name("firstName"));
//	  fName.sendKeys("Admin");
//	  System.out.println(fName.getText());
}