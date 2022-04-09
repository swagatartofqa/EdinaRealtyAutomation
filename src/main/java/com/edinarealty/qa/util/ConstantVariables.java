package com.edinarealty.qa.util;

import java.io.File;

public class ConstantVariables {
	//Drivers Path
	public String firefoxDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "drivers" + File.separator + "geckodriver";
	
	//Properties File Location
	public String propertiesFileLocation = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "edinarealty" + File.separator + "qa" + File.separator + "config" + File.separator + "config.properties";
	
	//Screenshot location
	public String screenshotLocation = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator;
//	public String screenshotLocation2 = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator;
	
	//Report Location
//	public String extentFactoryReportLocation = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "TestSuiteResults.html";
	public String extentFactoryReportLocation = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReport.html";
	
	//DataTable
	public String dataTable = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "edinarealty" + File.separator + "qa" + File.separator + "testdata" + File.separator + "TestData.xlsx";
}