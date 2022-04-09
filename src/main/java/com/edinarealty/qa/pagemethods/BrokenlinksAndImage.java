package com.edinarealty.qa.pagemethods;

import com.edinarealty.qa.pagelocators.LoginPageLocators;
import com.edinarealty.qa.util.GeneralUtil;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

public class BrokenlinksAndImage extends LoginPageLocators {

	public BrokenlinksAndImage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
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
