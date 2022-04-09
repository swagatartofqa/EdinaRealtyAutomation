package com.edinarealty.qa.pagemethods;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.edinarealty.qa.pagelocators.AccountPageLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AccountPageMethods extends AccountPageLocators {
	
	public AccountPageMethods(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
//	public static String getMessage() {
//		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
//				.filter(m -> m.getTo().equals("+19402835138")).map(Message::getBody).findFirst()
//				.orElseThrow(IllegalStateException::new);
//	}
	
	public void updateName(String firstName, String lastName) {
		//Output to the system and report
		System.out.println("Updating Full Name");
		reportLogger.log(LogStatus.INFO, "Updating Full Name");
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//
		findAHomeButton.click();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Go to the Saved Search page
		Actions actions = new Actions(eDriver);
		actions.moveToElement(userAccountDropDown).perform();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Click the 'Saved Searches' button
		actions.click(accountSettingsButton).click().perform();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Enter the first name
		accountFirstNameField.clear();
		accountFirstNameField.sendKeys(firstName);
		
		//Enter the last name
		accountLastNameField.clear();
		accountLastNameField.sendKeys(lastName);
		
		genMethods.waitForMilliseconds(3000);
		
		//Update the account name button
		updateAccountNameButton.click();
		
		genMethods.waitForMilliseconds(3000);
	}
	
	public void updatePhoneNumber(String phoneNumber) {
		//Output to the system and report
		System.out.println("Updating phone number");
		reportLogger.log(LogStatus.INFO, "Updating phone number");
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//
		findAHomeButton.click();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Go to the Saved Search page
		Actions actions = new Actions(eDriver);
		actions.moveToElement(userAccountDropDown).perform();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Click the 'Saved Searches' button
		actions.click(accountSettingsButton).click().perform();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(9000);
		
		//Enter the email
//		if (accountPhoneField.getAttribute("value").equalsIgnoreCase("")) {
//			System.out.println(accountPhoneField.getAttribute("value"));
//			accountPhoneField.sendKeys(phoneNumber);
//		}
		if (accountPhoneField.get(1).getAttribute("value").equalsIgnoreCase("")) {
			System.out.println(accountPhoneField.get(1).getAttribute("value"));
			accountPhoneField.get(1).sendKeys(phoneNumber);
		}
//		accountPhoneField.clear();
		
		
		genMethods.waitForMilliseconds(3000);
		
		//Update the account name button
		updateAccountPhoneButton.click();
		
		genMethods.waitForMilliseconds(3000);
	}
	
	public void updatePhoneNumberPart2(String username, String password, String phoneNumber) {
		//Output to the system and report
		System.out.println("Updating phone number, Step #2");
		reportLogger.log(LogStatus.INFO, "Updating phone number, Step #2");
		
		//
		updateAccountPhoneNumberButton.click();
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		signinViaEmailButton.click();
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		//Enter Login Credentials
		signinEmailAddressField.sendKeys(username);
		signinPasswordField.sendKeys(password);
		
		//Click 'Login'
		signinConfirmButton.click();
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		//Enter the Phone Number to Receive the OTP Code
		enterPhonNumberField.sendKeys(phoneNumber);
		
		//Click to Send the Code
		sendOTPCodeButton.click();
	}
	
	public void confirmOTPCode(String otpCode) {
		//
		verifyOTPCodeField.sendKeys(otpCode);
		
		//
		verifyOTPCodeButton.click();
	}
	
	public void updateEmail(String firstName, String lastName, String email) {
		//Output to the system and report
		System.out.println("Updating email");
		reportLogger.log(LogStatus.INFO, "Updating email");
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//
		findAHomeButton.click();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Go to the Saved Search page
		Actions actions = new Actions(eDriver);
		actions.moveToElement(userAccountDropDown).perform();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Click the 'Saved Searches' button
		actions.click(accountSettingsButton).click().perform();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Enter the email
		accountEmailField.clear();
		accountEmailField.sendKeys(email);
		
		genMethods.waitForMilliseconds(3000);
		
		//Update the account name button
		updateAccountNameButton.click();
		
		genMethods.waitForMilliseconds(3000);
	}
	
	public void verifySavedInfoViaRefresh(SoftAssert softAssert, String firstName, String lastName) {
		//Output to the system and report
		System.out.println("Verifying that the saved Settings are saved properly");
		reportLogger.log(LogStatus.INFO, "Verifying that the saved Settings are saved properly");
		
		try {
			if (updatedNameLabel.isDisplayed()) {
				softAssert.assertEquals("The 'Successfully Updated Name' label appeared", "The 'Successfully Updated Name' label appeared");
			} else {
				softAssert.assertEquals("The 'Successfully Updated Name' label did not appear", "The 'Successfully Updated Name' label appeared");
			}
		} catch (Exception e) {
			softAssert.assertEquals("The 'Successfully Updated Name' label did not appear", "The 'Successfully Updated Name' label appeared");
		}
		
		eDriver.navigate().refresh();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//
		softAssert.assertEquals(accountFirstNameField.getAttribute("value"), firstName);
		softAssert.assertEquals(accountLastNameField.getAttribute("value"), lastName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(9000);
	}
	
	public void verifySavedInfo(SoftAssert softAssert, String firstName, String lastName) {
		//Output to the system and report
		System.out.println("Verifying that the saved Settings are saved properly");
		reportLogger.log(LogStatus.INFO, "Verifying that the saved Settings are saved properly");
		
		findAHomeButton.click();
		
		genMethods.waitForMilliseconds(3000);
		
		//Go to the Saved Search page
		Actions actions = new Actions(eDriver);
		actions.moveToElement(userAccountDropDown).perform();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Click the 'Saved Searches' button
		actions.click(accountSettingsButton).click().perform();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//
		softAssert.assertEquals(accountFirstNameField.getAttribute("value"), firstName);
		softAssert.assertEquals(accountLastNameField.getAttribute("value"), lastName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(9000);
	}
	
	public void verifyEmailViaRefresh(SoftAssert softAssert, String email) {
		//Output to the system and report
		System.out.println("Verifying that the saved Settings are saved properly");
		reportLogger.log(LogStatus.INFO, "Verifying that the saved Settings are saved properly");
		
		try {
			if (updatedNameLabel.isDisplayed()) {
				softAssert.assertEquals("The 'Successfully Updated Name' label appeared", "The 'Successfully Updated Name' label appeared");
			} else {
				softAssert.assertEquals("The 'Successfully Updated Name' label did not appear", "The 'Successfully Updated Name' label appeared");
			}
		} catch (Exception e) {
			softAssert.assertEquals("The 'Successfully Updated Name' label did not appear", "The 'Successfully Updated Name' label appeared");
		}
		
		eDriver.navigate().refresh();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
//		System.out.println(accountEmailField.getAttribute("value"));
		
		//
		softAssert.assertEquals(accountFirstNameField.getAttribute("value"), email);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(9000);
	}
	
	public void verifyEmail(SoftAssert softAssert, String email) {
		//Output to the system and report
		System.out.println("Verifying that the saved Settings are saved properly");
		reportLogger.log(LogStatus.INFO, "Verifying that the saved Settings are saved properly");
		
		findAHomeButton.click();
		
		genMethods.waitForMilliseconds(3000);
		
		//Go to the Saved Search page
		Actions actions = new Actions(eDriver);
		actions.moveToElement(userAccountDropDown).perform();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Click the 'Saved Searches' button
		actions.click(accountSettingsButton).click().perform();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
//		System.out.println(accountEmailField.getAttribute("value"));
		
		//
		softAssert.assertEquals(accountEmailField.getAttribute("value"), email);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(9000);
	}
	
}