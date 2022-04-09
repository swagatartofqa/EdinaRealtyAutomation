package com.edinarealty.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.edinarealty.qa.pagemethods.AccountPageMethods;
import com.edinarealty.qa.pagemethods.LoginPageMethods;
import com.edinarealty.qa.pagemethods.SearchPageMethods;
import com.edinarealty.qa.util.ConstantVariables;
import com.edinarealty.qa.util.EventHandler;
import com.edinarealty.qa.util.ExcelMethods;
import com.edinarealty.qa.util.ExtentFactory;
import com.edinarealty.qa.util.GeneralMethods;
import com.edinarealty.qa.util.GoogleAPI;
import com.edinarealty.qa.util.ReportLoggerMethods;
import com.edinarealty.qa.util.OTPMethods;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	//Initialize BrowserStack Variable(s)
	public static final String USERNAME = "erwtestdang_UGud5h";
	public static final String AUTOMATE_KEY = "oy1Dq2xEt4UHkNxepdpd";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	protected static String activeBrowser = "";
	protected static String activeBrowserStack = "";
	
	//Define Variable(s)
	protected static Properties prop;
	protected static ConstantVariables constantVariables;
	protected ExcelMethods excelMethods;
	protected GeneralMethods genMethods;
	protected OTPMethods otpMethods;
	protected GoogleAPI googleAPI;
	protected ReportLoggerMethods reportLoggerMethods;
	protected String path;
	protected String sheetName;
	
	//Variable(s) used to export script results
	protected int iteration;
	protected int column;
	protected int retryCount = 0;
	protected int maxRetryCount = 3;
	
	//Variable(s) used to initialize the WebDriver
	protected static DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	private static WebDriver driver; //used to setup a connection to one or more browsers for testing
	private static EventHandler eHandler;  //used in conjunction with the EventFiringWebDriver
	protected static EventFiringWebDriver eDriver; //used in conjunction with the WebDriver
	
	//Setup the report logger
	protected ExtentReports report; //used to setup a report that will hold the testing info of the script(s)
	protected ExtentTest reportLogger; //used to store testing details in the report
	
	//Declare PageFactories
	protected LoginPageMethods loginPageMethods;
	protected SearchPageMethods searchPageMethods;
	protected AccountPageMethods accountPageMethods;
	
	public TestBase() {
		try {
			constantVariables = new ConstantVariables();
			prop = new Properties();
			FileInputStream ip = new FileInputStream(constantVariables.propertiesFileLocation);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initializeDriver(String browser, String browserStack, String os, String os_version, String browserVersion) {
		String testingBrowser = browser;
		
		//
		activeBrowser = browser;
		activeBrowserStack  = browserStack;
		
		//Set BrowserStack browser details to 'caps'
//		String methodName = name.getName();
//		caps.setCapability("os", "Windows");
//		caps.setCapability("os_version", "10");
//		caps.setCapability("browser_version", "latest");
//		caps.setCapability("name", methodName);
		
		caps.setCapability("os", os);
		caps.setCapability("os_version", os_version);
		caps.setCapability("browser_version", browserVersion);
		
//		caps.setCapability("device", "iPhone 13 Pro Max");
//		caps.setCapability("os_version", "15");
//		caps.setCapability("browserName", "ios");
//		caps.setCapability("realMobile", "true");
		
		//Initialize the relevant browser driver
		if (testingBrowser.equalsIgnoreCase("edge")) {
			//Setup caps properties for IE, if IE is going to be used as the Driver
			caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			
			WebDriverManager.edgedriver().setup();
			
			if (browserStack.equalsIgnoreCase("yes")) {
				caps.setCapability("browser", "Edge");
			} else {
				driver = new EdgeDriver();
			}
		} else if (testingBrowser.equalsIgnoreCase("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver.gecko.driver", constantVariables.firefoxDriverPath);
			
			if (browserStack.equalsIgnoreCase("y")) {
				caps.setCapability("browser", "Firefox");
			} else {
				driver = new FirefoxDriver();
			}
		} else if (testingBrowser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			if (browserStack.equalsIgnoreCase("y")) {
				caps.setCapability("browser", "Chrome");
			} else {
				driver = new ChromeDriver();
			}
		} else if (testingBrowser.equalsIgnoreCase("safari")) {
			//~~~~~~~~ Safari needs extra testing/work ~~~~~~~~//
			WebDriverManager.safaridriver().setup();
			if (browserStack.equalsIgnoreCase("y")) {
				caps.setCapability("browser", "Safari");
			} else {
				driver = new SafariDriver();
			}
		} else {
			System.out.println("Cannot setup the driver due to invalid input");
			driver.quit();
		}
		
		if (browserStack.equalsIgnoreCase("y")) {
			try {
				driver = new RemoteWebDriver(new URL(URL), caps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		//Setup the Event Driver
		eDriver = new EventFiringWebDriver(driver);
		eHandler = new EventHandler();
		eDriver.register(eHandler);
		
		eDriver.manage().window().maximize();
		eDriver.manage().deleteAllCookies();
		eDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		eDriver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
	}
	
	@BeforeTest
	@Parameters({"browser", "browserStack", "os", "os_version", "browserVersion"})
	public void beforeTest(String browser, String browserStack, String os, String os_version, String browserVersion) {
		System.out.println("Performing the script's setups (@BeforeSuite)");
		
		//Delete previous Extent Report
		ExtentFactory.deleteExtentReport();
		
		//Initialize Variable(s)
		initializeDriver(browser, browserStack, os, os_version, browserVersion); //Sets up WebDriver with Listeners
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	@AfterSuite
	public void afterSuite() {
		eDriver.quit();
	}
	
//	@AfterMethod
//	public void afterMethod(ITestResult result) throws IOException {
//		try {
//			if(result.getStatus() == ITestResult.FAILURE) {
//				//Output the Script status to the Report & Excel
//				excelMethods.setDataTableCell("Failure - " + excelMethods.getCurrentDateTime(), iteration, column);
//				reportLogger.log(LogStatus.FAIL, "Failure: The test case '" + result.getName() + "' failed");
////				reportLogger.log(LogStatus.FAIL,  "The Test Case that failed is: " + result.getName()); //adds name to ExtentReport
////				reportLogger.log(LogStatus.FAIL,  "The Test Case that failed is: " + result.getThrowable()); //adds error/exception to ExtentReport
//			} else if (result.getStatus() == ITestResult.SKIP) {
//				//Output the Script status to the Report & Excel
//				System.out.println("The Test Case that was skipped is: " + result.getName());
////				reportLogger.log(LogStatus.SKIP,  "The Test Case that was skipped is: " + result.getName());
//			} else if (result.getStatus() == ITestResult.SUCCESS) {
//				//Output the Script status to the Report & Excel
//				excelMethods.setDataTableCell("Success - " + excelMethods.getCurrentDateTime(), iteration, column);
//				System.out.println("The Test Case that passed is: " + result.getName());
////				reportLogger.log(LogStatus.PASS,  "The Test Case that passed is: " + result.getName());
//			} else {
//				//Output the Script status to the Report & Excel
////				reportLogger.log(LogStatus.UNKNOWN,  "Unknown error upon completion of a @Test - the @Test neither passed, failed, or was skipped");
//			}
//			
//			//End/Tie-up the Report
//			report.endTest(reportLogger);
//			report.flush();
//		} catch (Exception e) {
//			System.out.print("");
//		}
//	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		try {
			if(result.getStatus() == ITestResult.SUCCESS) {
				//Output the Script status to the Report & Excel
				excelMethods.setDataTableCell("Success - " + excelMethods.getCurrentDateTime(), iteration, column);
				System.out.println("The Test Case that passed is: " + result.getName());
				reportLogger.log(LogStatus.PASS,  "The Test Case that passed is: " + result.getName());
			} else if(result.getStatus() == ITestResult.FAILURE) {
				//Output the Script status to the Report & Excel
				excelMethods.setDataTableCell("Failure - " + excelMethods.getCurrentDateTime(), iteration, column);
				reportLogger.log(LogStatus.FAIL, "Failure: The test case '" + result.getName() + "' failed");
//				reportLogger.log(LogStatus.FAIL,  "The Test Case that failed is: " + result.getName()); //adds name to ExtentReport
//				reportLogger.log(LogStatus.FAIL,  "The Test Case that failed is: " + result.getThrowable()); //adds error/exception to ExtentReport
			} else if (result.getStatus() == ITestResult.SKIP) {
				//Output the Script status to the Report & Excel
				System.out.println("The Test Case that was skipped is: " + result.getName());
				reportLogger.log(LogStatus.SKIP,  "The Test Case that was skipped is: " + result.getName());
			} else {
				//Output the Script status to the Report & Excel
				reportLogger.log(LogStatus.UNKNOWN,  "Unknown error upon completion of a @Test - the @Test neither passed, failed, or was skipped");
			}
			
			//Reset the retry counter to zero
			retryCount = 0;
			
			//End/Tie-up the Report
			report.endTest(reportLogger);
			report.flush();
		} catch (Exception e) {
			System.out.print("");
		}
	}
	
}