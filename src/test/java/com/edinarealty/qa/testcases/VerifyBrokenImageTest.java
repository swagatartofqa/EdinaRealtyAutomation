package com.edinarealty.qa.testcases;

import com.edinarealty.qa.base.TestBase;
import com.edinarealty.qa.pagemethods.BrokenlinksAndImage;
import com.edinarealty.qa.pagemethods.LoginPageMethods;
import com.edinarealty.qa.pagemethods.SearchPageMethods;
import com.edinarealty.qa.util.ExcelMethods;
import com.edinarealty.qa.util.ExtentFactory;
import com.edinarealty.qa.util.GeneralMethods;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyBrokenImageTest extends TestBase {

    //Define Variable(s)
    SoftAssert checkpoint;

    //Constructor
    public VerifyBrokenImageTest() {
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
        brokenlinksAndImage =new BrokenlinksAndImage(eDriver,reportLogger);
    }

    //Setup variable(s) and other info for the class
    @BeforeClass
    @Parameters({"dataTable"})
    public void beforeClass(String excelPath) {
        //Initialize Variable(s)
        genMethods = new GeneralMethods();
        excelMethods = new ExcelMethods();
        excelMethods.setDataTablePath(excelPath);
        excelMethods.setSheetName("VerifyBrokenImage");
        column = 11;
    }
    //Test the login functionality
    @Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
    public void VerifyBrokenImage(String active, String reportTitle, String website, String accountType, String userid, String password, String multiFactorAuthentication, String expectedSuccessOrFailure, String expectedLoginName, String signout, String finalResult, String dataRow) {
        System.out.println("@Test - loginTest()");

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
//			eDriver.manage().deleteAllCookies();
            //Navigate to the website
            loginPageMethods.navigateToWebsite(website);

            //Attempt to login
            loginPageMethods.login(userid, password);

            //Pause the script for a bit
            genMethods.waitForMilliseconds(8000);
            genMethods.waitForMilliseconds(12000);
            //Verify if the login was successful or failed
            if (expectedSuccessOrFailure.equalsIgnoreCase("success")) {
                //Verify if the login was successful
                loginPageMethods.verifySuccessfulLogin(checkpoint, expectedLoginName);
            } else {
                //Verify if the failed login generated the expected error message
                loginPageMethods.verifyFailedLoginErrorMessage(checkpoint);
            }

            brokenlinksAndImage.verifyTopLinksHaveValidImages();

            //Attempt to logout, if desired
            if (signout.equalsIgnoreCase("y") || signout.equalsIgnoreCase("yes")) {
                loginPageMethods.logout();
            }

            //Assert all Checkpoints
            checkpoint.assertAll();
        } else {
            System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
        }
    }
}
