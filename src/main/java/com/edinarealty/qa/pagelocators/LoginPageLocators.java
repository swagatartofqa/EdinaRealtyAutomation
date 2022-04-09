package com.edinarealty.qa.pagelocators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.edinarealty.qa.util.GeneralMethods;
import com.edinarealty.qa.util.GoogleAPI;
import com.edinarealty.qa.util.OTPMethods;
import com.edinarealty.qa.util.ReportLoggerMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class LoginPageLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	protected OTPMethods otpMethods;
	protected GoogleAPI googleAPI;
	
	//Constructor
	public LoginPageLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
		
		otpMethods = new OTPMethods();
		googleAPI = new GoogleAPI();
	}
	
	// ~~~ Main Locators ~~~ //
	
	//Main Page
	@FindBy(id="searchBtn")
	protected WebElement searchButton;
	
	// ~~~ Login Locators ~~~ //
	
	//Login
	@FindBy(id="signinAjax")
	protected WebElement signinPageButton;
	
	@FindBy(xpath="(//i[@class='user'])[2]")
	protected WebElement signinPageButton2;
	
	@FindBy(id="signInName")
	protected WebElement emailField;
	
	@FindBy(id="password")
	protected WebElement passwordField;
	
	@FindBy(id="rememberMe")
	protected WebElement rememberMeCheckbox;
	
	@FindBy(id="next")
	protected WebElement signinButton;
	
	@FindBy(xpath="//div[@class='error pageLevel']//p")
	protected WebElement loginErrorMessage;
	
	@FindBy(xpath="//div[@class='nameHeader']//div[@class='nameText']")
	protected List<WebElement> loginNameList;
	
	//Logout
	@FindBy(xpath="//span[@class='nameInitals']")
	protected WebElement userAccountDropDown;
	
	@FindBy(xpath="//a[contains(text(), 'Sign out')]")
	protected WebElement signoutButton;
	
	// ~~~ Create Account Locators ~~~ //
	
	//Create Account
	@FindBy(xpath="//a[contains(text(), 'Sign up now')]")
	protected WebElement signupButton;
	
	@FindBy(id="email")
	protected WebElement newEmailAddressField;
	
	@FindBy(id="emailVerificationControl_but_send_code")
	protected WebElement sendVerificationButton;
	
	@FindBy(id="VerificationCode")
	protected WebElement verificationCodeField;
	
	@FindBy(id="emailVerificationControl_but_verify_code")
	protected WebElement verifyCodeButton;
	
	@FindBy(xpath="//div[contains(text(), 'The code has been verified. You can now continue.')]")
	protected WebElement successfulVerificationLabel;
	
	@FindBy(id="givenName")
	protected WebElement newFirstNameField;
	
	@FindBy(id="surName")
	protected WebElement newLastNameField;
	
	@FindBy(id="newPassword")
	protected WebElement newPasswordField;
	
	@FindBy(id="reenterPassword")
	protected WebElement confirmNewPasswordField;
	
	@FindBy(id="mfaConsentChoice_AgreeToMfaYes")
	protected WebElement multiFactorAuthenticationCheckbox;
	
	@FindBy(id="continue")
	protected WebElement createAccountButton;
	
	//
	@FindBy(id="number")
	protected WebElement newPhoneNumberField;
	
	@FindBy(id="sendCode")
	protected WebElement sendPhoneNumberCodeButton;
	
	@FindBy(id="verificationCode")
	protected WebElement verifyPhoneNumberCodeField;
	
	@FindBy(id="verifyCode")
	protected WebElement verifyPhoneNumberCodeButton;
	
}