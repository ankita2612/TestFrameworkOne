package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener_Selenium extends SeleniumUtils implements ITestListener {

	public static int sheetIndex;

	// Executes when Test starts
	public void onTestStart(ITestResult iTestResult) {
	}

	// Executes for Test Successful
	public void onTestSuccess(ITestResult iTestResult) {
	}

	// Executes for Test Failure
	public void onTestFailure(ITestResult iTestResult) {

		// Getting parameters from testng.xml file
		String sheet = iTestResult.getTestContext().getCurrentXmlTest().getParameter("sheet");

		// Getting Method Name from results
		String methodName = iTestResult.getTestContext().getName().toString();

		sheetIndex = Integer.parseInt(sheet);

		takeScreenshot(methodName);

	}

	// Executes when Test get skipped
	public void onTestSkipped(ITestResult iTestResult) {
	}

	// Executes when specific % of Success
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	}

	public void onStart(ITestContext iTestContext) {
	}

}