package com.edinarealty.qa.pagemethods;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.edinarealty.qa.pagelocators.LoginPageLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

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
	
	public void createAccount(String email, String firstName, String lastName, String password) {
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
		
		//Click 'Sign up now'
		signupButton.click();
		
		//Enter the new email address
		newEmailAddressField.sendKeys(email);
		
		//Click 'Send verification code'
		sendVerificationButton.click();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Enter the Verification Code
		verificationCodeField.sendKeys(googleAPI.getEmailBody());
		
		//Click to verify the Verification Code
		verifyCodeButton.click();
		
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
		
		//Create the account
		createAccountButton.click();
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
		verificationCodeField.sendKeys(googleAPI.getEmailBody());
		
		//Click to verify the Verification Code
		verifyCodeButton.click();
		} catch (Exception e) {
			System.out.println(e);
		}
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
			
			//Enter the Verification Code
			verifyPhoneNumberCodeField.sendKeys(otpMethods.outputOTPNumber());
			
			//Submit the Verification Code
			verifyPhoneNumberCodeButton.click();
		} else {
			//Create the account
			createAccountButton.click();
		}
	}
}