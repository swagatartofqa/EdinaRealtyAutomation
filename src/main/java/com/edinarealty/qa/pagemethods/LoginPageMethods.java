package com.edinarealty.qa.pagemethods;

import com.edinarealty.qa.util.GeneralUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.edinarealty.qa.pagelocators.LoginPageLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

public class LoginPageMethods extends LoginPageLocators {

	public LoginPageMethods(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}

	public void navigateToWebsite(String website) {
		//Output to the system and report
		System.out.println("Navigating to the website -> " + website);
		reportLogger.log(LogStatus.INFO, "Navigating to the website -> " + website);

		//Navigate to the website
		for (int i = 0; i < 3; i++) {
			try {
				eDriver.get(website);
				return;
			} catch (Exception e) {

			}
		}
	}

	public void login(String userid, String password) {
		//Output to the system and report
		System.out.println("Attempting to login with the userid -> " + userid);
		reportLogger.log(LogStatus.INFO, "Attempting to login with the userid -> " + userid);

		//Navigate to another page (where the 'Sign in' page button can be accessed via automation due to a shorter absolute-path)
		searchButton.click();

		//Pause the script for a bit
		genMethods.waitForMilliseconds(4000);

		//Navigate to the 'Sign in' page
		try {
			signinPageButton.click();
		} catch (Exception e) {
			signinPageButton2.click();
		}

		//Pause the script for a bit
		genMethods.waitForMilliseconds(4000);

		//Enter the userid
		emailField.sendKeys(userid);

		//Enter the password
		passwordField.sendKeys(password);

		//Uncheck the 'Remember Me' checkbox, if it is checked
		if (rememberMeCheckbox.isSelected()) {
			rememberMeCheckbox.click();
		}

		//Click the 'Sign in' button
		signinButton.click();
	}

	public void verifyFailedLoginErrorMessage(SoftAssert softAssert) {
		//Output to the system and report
		System.out.println("Verifying if the failed login generated the expected error message");
		reportLogger.log(LogStatus.INFO, "Verifying if the failed login generated the expected error message");

		//Assert the failed login error message
		softAssert.assertEquals(loginErrorMessage.isDisplayed(), true);

		//Output the status to the System & Extent Report
		if (loginErrorMessage.isDisplayed()) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The error message displayed correctly");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The error message did not display", "verifyFailedLoginErrorMessage");
		}
	}

	public void verifySuccessfulLogin(SoftAssert softAssert, String expectedLoginName) {
		//Output to the system and report
		System.out.println("Verifying username '" + expectedLoginName + "' login successful");
		reportLogger.log(LogStatus.INFO, "Verifying username '" + expectedLoginName + "' login successful");

		//Initialize Variable(s)
		String loginName = "";

		//Retrieve the full name of the logged in user & place it into a single string variable
		for (int i = 0; i < loginNameList.size(); i++) {
			loginName += loginNameList.get(i).getText() + " ";
		}

		//Remove the last char of the full name (which will be an unnecessary space)
		if (loginName.length() != 0) {
			loginName = loginName.substring(0, loginName.length()-1);
		}
		System.out.println(loginName);
		System.out.println(expectedLoginName);
		//Assert the logged in user's full name
		softAssert.assertEquals(loginName, expectedLoginName);

		//Output the status to the System & Extent Report
		if (loginName.equals(expectedLoginName)) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The username '" + expectedLoginName + "' logged in correctly");
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The test case 'Local Account Login Test' passed");
		} else if (loginName.length() == 0) {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The username '" + expectedLoginName + "' was not logged in", "verifyLogin");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The user logged into the user '" + loginName + "' instead of the expected user '" + expectedLoginName + "'", "verifyLogin");
		}
	}

	public void logout() {
		//Output to the system and report
		System.out.println("Attempting to logout");
		reportLogger.log(LogStatus.INFO, "Attempting to logout");

		//Attempt to logout
		try {
			Actions actions = new Actions(eDriver);
			actions.moveToElement(userAccountDropDown).click(signoutButton).click().perform();
		} catch (Exception e) {

		}
	}

	public void createAccountUsingGoogleAccount(String email, String firstName, String lastName, String password) throws Exception {
		//Output to the system and report
		System.out.println("Creating a new Account");
		reportLogger.log(LogStatus.INFO, "Creating a new Account");

		//Navigate to another page (where the 'Sign in' page button can be accessed via automation due to a shorter absolute-path)
		searchButton.click();

		//Pause the script for a bit
		genMethods.waitForMilliseconds(4000);

		//Navigate to the 'Sign in' page
		try {
			signinPageButton.click();
		} catch (Exception e) {
			signinPageButton2.click();
		}

		//Pause the script for a bit
		genMethods.waitForMilliseconds(4000);

		signInSignUpUsingGoogle.click();
		genMethods.waitForMilliseconds(4000);
		if(eDriver.findElements(By.xpath("//div[text()='Use another account']")).size()>0){
			userAnotherAccount.click();
		}

		enterEmail.click();
		if(eDriver.findElements(By.xpath("//input[@aria-label='Email or phone']")).size()>0) {
			StringBuffer str = new StringBuffer(email);
			String newemail= str.insert(4,GeneralUtil.generatingRandomNumericString()).toString();

			enterEmail.sendKeys(newemail);
			genMethods.waitForMilliseconds(2000);
			next.click();
			genMethods.waitForMilliseconds(4000);
			enterPassword.sendKeys(password);
			next.click();
		}
		genMethods.waitForMilliseconds(2000);
		sendVerificationButton.click();
		genMethods.waitForMilliseconds(9000);
		//Enter the Verification Code
		verificationCodeField.sendKeys(googleAPI.getEmailBody("notifications"));

		genMethods.waitForMilliseconds(2000);
		verifyCodeButton.click();

		genMethods.waitForMilliseconds(4000);
		//Send a failure due to the new email verification failing
		try {
			if (!successfulVerificationLabel.isDisplayed()) {
				reportLoggerMethods.reportFailedCheckpoint("Failure: The verification process for the new email failed", "VerifyNewEmail");
				Assert.assertEquals("Verification of the new email did not pass", "Verification of the new email passed");
			}
		} catch (Exception e) {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The verification process for the new email failed", "VerifyNewEmail");
			Assert.assertEquals("Verification of the new email did not pass", "Verification of the new email passed");
		}

		//Click 'Sign up'
		signUp_button.click();
		genMethods.waitForMilliseconds(6000);
		newFirstNameField.clear();

		newFirstNameField.sendKeys(firstName);
		newLastNameField.clear();
		newLastNameField.sendKeys(lastName);
		//Click 'Sign up'
		signUp_button.click();
		genMethods.waitForMilliseconds(9000);

	}

	public void createAccount(String email, String firstName, String lastName, String password, String multiFactorAuthentication, String phoneNumber) {
		//Output to the system and report
		System.out.println("Creating a new Account");
		reportLogger.log(LogStatus.INFO, "Creating a new Account");
		try {
			//Navigate to another page (where the 'Sign in' page button can be accessed via automation due to a shorter absolute-path)
			searchButton.click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(4000);

			//Navigate to the 'Sign in' page
			try {
				signinPageButton.click();
			} catch (Exception e) {
				signinPageButton2.click();
			}

			//Pause the script for a bit
			genMethods.waitForMilliseconds(4000);

			//Click 'Sign up now'
			signupButton.click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(4000);

			//Enter the new email address
			newEmailAddressField.sendKeys(email);

			//Pause the script for a bit
			genMethods.waitForMilliseconds(2000);

			//Click 'Send verification code'
			sendVerificationButton.click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(8000);

			//Enter the Verification Code
			verificationCodeField.sendKeys(googleAPI.getEmailBody("notifications"));

			//Click to verify the Verification Code
			verifyCodeButton.click();
		} catch (Exception e) {
			System.out.println(e);
		}
		genMethods.waitForMilliseconds(9000);
		//Send a failure due to the new email verification failing
		try {
			if (!successfulVerificationLabel.isDisplayed()) {
				reportLoggerMethods.reportFailedCheckpoint("Failure: The verification process for the new email failed", "VerifyNewEmail");
				Assert.assertEquals("Verification of the new email did not pass", "Verification of the new email passed");
			}
		} catch (Exception e) {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The verification process for the new email failed", "VerifyNewEmail");
			Assert.assertEquals("Verification of the new email did not pass", "Verification of the new email passed");
		}

		//Enter First Name
		newFirstNameField.sendKeys(firstName);

		//Enter Last Name
		newLastNameField.sendKeys(lastName);

		//Enter the new password
		newPasswordField.sendKeys(password);

		//Enter the confirmation for the new password
		confirmNewPasswordField.sendKeys(password);

		//Check if enabling 'Multi-Factor Authenticatioon' is desired
		if (multiFactorAuthentication.equalsIgnoreCase("y") || multiFactorAuthentication.equalsIgnoreCase("yes")) {
			//Check the 'Multi-Factor Authenticatioon' checkbox
			multiFactorAuthenticationCheckbox.click();

			//Create the account
			createAccountButton.click();

			//Enter the new Phone Number
			newPhoneNumberField.sendKeys(phoneNumber);

			//Click to send a code to the phone number for the Multi-Factor Authentication
			sendPhoneNumberCodeButton.click();

			//Pause the script for a bit
			genMethods.waitForMilliseconds(4000);

			verifyPhoneNumberCodeField.click();
			System.out.println(otpMethods.outputOTPNumber());
			//Enter the Verification Code
			verifyPhoneNumberCodeField.sendKeys(otpMethods.outputOTPNumber());

			//Submit the Verification Code
			verifyPhoneNumberCodeButton.click();
		} else {

			//Create the account
			createAccountButton.click();
		}
	}

	public void createAccountUsingMicrosoftAccount(String email, String firstName, String lastName, String password) throws Exception {
		//Output to the system and report
		System.out.println("Creating a new Account");
		reportLogger.log(LogStatus.INFO, "Creating a new Account");

		//Navigate to another page (where the 'Sign in' page button can be accessed via automation due to a shorter absolute-path)
		searchButton.click();

		//Pause the script for a bit
		genMethods.waitForMilliseconds(4000);

		//Navigate to the 'Sign in' page
		try {
			signinPageButton.click();
		} catch (Exception e) {
			signinPageButton2.click();
		}

		//Pause the script for a bit
		genMethods.waitForMilliseconds(4000);

		signInSignUpUsingMicrosoft.click();
		genMethods.waitForMilliseconds(4000);

		enterMicrosoftEmail.click();
		if(eDriver.findElements(By.xpath("//input[@type='email']")).size()>0) {
			enterMicrosoftEmail.sendKeys(email);
			genMethods.waitForMilliseconds(2000);
			MicrosoftNext_Button.click();
			genMethods.waitForMilliseconds(4000);
		/*	if(eDriver.findElements(By.xpath("//a[contains(text(),'Email code to')]")).size()>0){
				EmailCodeTo_Link.click();
			}
*/
			if(eDriver.findElements(By.xpath("//a[contains(text(),'Use your password instead')]")).size()>0){
				UsePassword_Link.click();
			}
		}

		genMethods.waitForMilliseconds(10000);
		//	System.out.println(googleAPI.getEmailBodyForMicrosoft("Microsoft"));
		inp_microsoftPassword.click();
		//Enter the Verification Code
		inp_microsoftPassword.sendKeys(password);

		MicrosoftNext_Button.click();

		if(eDriver.findElements(By.xpath("//input[@type='submit']")).size()>0){
			MicrosoftNext_Button.click();
		}
		if(eDriver.findElements(By.xpath("(//input[@type='submit'])[2]")).size()>0){
			Microsoftyes_Button.click();
		}

		genMethods.waitForMilliseconds(8000);
		StringBuffer str = new StringBuffer("Test@artofqa.com");
		String newemail= str.insert(4,GeneralUtil.generatingRandomNumericString()).toString();
		System.out.println(newemail);
		newEmailAddressField.clear();
		newEmailAddressField.sendKeys(newemail);
		sendVerificationButton.click();
		genMethods.waitForMilliseconds(9000);
		//Enter the Verification Code
		verificationCodeField.sendKeys(googleAPI.getEmailBody("notifications"));
		genMethods.waitForMilliseconds(7000);
		verifyCodeButton.click();
		genMethods.waitForMilliseconds(4000);
		//Send a failure due to the new email verification failing
		try {
			if (!successfulVerificationLabel.isDisplayed()) {
				reportLoggerMethods.reportFailedCheckpoint("Failure: The verification process for the new email failed", "VerifyNewEmail");
				Assert.assertEquals("Verification of the new email did not pass", "Verification of the new email passed");
			}
		} catch (Exception e) {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The verification process for the new email failed", "VerifyNewEmail");
			Assert.assertEquals("Verification of the new email did not pass", "Verification of the new email passed");
		}

		//Click 'Sign up'
		signUp_button.click();
		genMethods.waitForMilliseconds(6000);
		newFirstNameField.clear();
		newFirstNameField.sendKeys(firstName);
		newLastNameField.clear();
		newLastNameField.sendKeys(lastName);
		//Click 'Sign up'
		signUp_button.click();
		genMethods.waitForMilliseconds(9000);

	}

	public void verifyAllBrokenLinks() {
		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;

		List<WebElement> links = eDriver.findElements(By.tagName("a"));
		System.out.println(links.size());
		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {

			url = it.next().getAttribute("href");

			System.out.println(url);

			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			try {
				huc = (HttpURLConnection)(new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if(respCode >= 400){
					reportLogger.log(LogStatus.FAIL,url +" is a broken link");
					System.out.println(url+" is a broken link");

				}
				else{
					reportLogger.log(LogStatus.PASS,url +" is a valid link");
					System.out.println(url+" is a valid link");
				}

			} catch (MalformedURLException e) {
				System.out.println(e.toString());
			} catch (Exception e) {
// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

	public void verifyTopLinksHaveValidLinks() {
		reportLogger.log(LogStatus.INFO," Verifying Broken Links on Realtor Career Page");
		RealtorCareerLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenLinks();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Links on Find a Home Page");
		FindaHomelink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenLinks();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Links on Find a Realtor Page");
		FindaRealtorLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenLinks();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Links on Mortgage Page");
		MortagageLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenLinks();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Links on SellLink Page");
		SellLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenLinks();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Links on Insights Link Page");
		InsightsLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenLinks();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Links on Services Link Page");
		ServicesLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenLinks();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Links on Contact Link Page");
		ContactLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenLinks();
		eDriver.navigate().back();
	}



	public void verifyTopLinksHaveValidImages() {
		reportLogger.log(LogStatus.INFO," Verifying Broken Image on Realtor Career Page");
		RealtorCareerLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenImages();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Image on Find a Home Page");
		FindaHomelink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenImages();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Image on Find a Realtor Page");
		FindaRealtorLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenImages();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Image on Mortgage Page");
		MortagageLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenImages();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Image on SellLink Page");
		SellLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenImages();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Image on Insights Link Page");
		InsightsLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenImages();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Image on Services Link Page");
		ServicesLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenImages();
		eDriver.navigate().back();
		genMethods.waitForMilliseconds(6000);
		reportLogger.log(LogStatus.INFO," Verifying Broken Images on Contact Link Page");
		ContactLink.click();
		genMethods.waitForMilliseconds(6000);
		this.verifyAllBrokenImages();
		eDriver.navigate().back();
	}



	public void verifyAllBrokenImages() {
		URL url = null;
		HttpURLConnection huc = null;


		List<WebElement> images = eDriver.findElements(By.tagName("img"));
		System.out.println(images.size());
		for (WebElement image: images){
			String imgsrc = image.getAttribute("src");
			try {
				url = new URL(imgsrc);
				URLConnection urlConnection= url.openConnection();
				HttpURLConnection httpURLConnection= (HttpURLConnection) urlConnection;
				httpURLConnection.setConnectTimeout(7000);
				httpURLConnection.connect();

				if(httpURLConnection.getResponseCode()==200){
					reportLogger.log(LogStatus.PASS,imgsrc +" >> " + httpURLConnection.getResponseMessage());
				}else{
					reportLogger.log(LogStatus.FAIL,imgsrc +" >> " + httpURLConnection.getResponseMessage());
				}
				httpURLConnection.disconnect();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}


	}
}
