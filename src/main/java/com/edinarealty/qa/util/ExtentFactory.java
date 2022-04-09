package com.edinarealty.qa.util;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {
	
	private static ConstantVariables constantVariables = new ConstantVariables();
	private static String path = constantVariables.extentFactoryReportLocation;
	
	public static ExtentReports getInstance(String browser, String browserStack) {
		ExtentReports extent;
		extent = new ExtentReports(path, false);
		extent.addSystemInfo("Browser", browser);
		
		if (browserStack.equalsIgnoreCase("y")) {
			extent.addSystemInfo("Browser Stack", "Yes");
		} else {
			extent.addSystemInfo("Browser Stack", "No");
		}
		
		return extent;
	}
	
	public static void deleteExtentReport() {
		File file = new File(path);
		
		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete file, located at: " + path);
		}
	}
	
}