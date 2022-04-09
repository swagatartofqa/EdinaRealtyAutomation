package com.edinarealty.qa.util;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.edinarealty.qa.base.TestBase;

public class Listeners extends TestBase implements IInvokedMethodListener, ITestListener, ISuiteListener {
	
	@Override
	public void onStart(ISuite suite) {
		
	}
	
	@Override
	public void onFinish(ISuite suite) {
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure -> Test Name: " + result.getName() + " in class name: " + result.getClass());
		System.out.println("onTestFailure -> Test Status: " + result.getStatus() + "/" + result.toString());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("onTestSkipped -> Test Name: " + result.getName() + " / " + result.getName() + "-" + result.getMethod());
		System.out.println("Test Skipped due to:");
		System.out.println(result.getSkipCausedBy());
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	
	@Override
	public void onStart(ITestContext context) {
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		
	}
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		
	}
	
	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		
	}
	
}