package com.employees.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	public void onStart(ITestContext testContext) {
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/myReport.html"); // Report Location
	
		htmlReporter.config().setDocumentTitle("Automation Report");  // Title of Report
		htmlReporter.config().setReportName("Rest API Testing Report"); // Name of Report 
//		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // Location of Report
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project name", "Employee Database API");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Ali Faizi");
	}
	
	public void onTestSuccess(ITestResult result) {
		
	// test = extent.CreateTest(result.getClass().getName());
	// test.createNode(result.getName());
		test = extent.createTest(result.getName()); // Create new entry in Report
		
		test.log(Status.PASS, "The PASSED Test Case is " + result.getName());
		
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName()); // Create new entry in Report
		
		test.log(Status.FAIL, "The FAILED Test Case is " + result.getName()); // To add name in extent report
		test.log(Status.FAIL, "The FAILED Test Case is " + result.getThrowable()); // To add error/exception in extent Report
	}

	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getName()); // Create new entry in Report
		test.log(Status.SKIP, "The SKIPPED Test Case is " + result.getName());
	}
	
	public void  onFinish(ITestContext testContext) {
		extent.flush();
	}
}
