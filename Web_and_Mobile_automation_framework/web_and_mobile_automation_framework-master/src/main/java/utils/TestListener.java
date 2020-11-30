package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends AppiumUtils implements ITestListener {

	public static int deviceIndex;
	public static String deviceName;
	public static int sheetIndex;

	//Executes when Test starts
	public void onTestStart(ITestResult iTestResult) {
	}
	
	//Executes for Test Successful
	public void onTestSuccess(ITestResult iTestResult) {
	}

	//Executes for Test Failure
	public void onTestFailure(ITestResult iTestResult) {

		//Getting parameters from testng.xml file
		String index = iTestResult.getTestContext().getCurrentXmlTest().getParameter("index");
		String sheet = iTestResult.getTestContext().getCurrentXmlTest().getParameter("sheet");
		String propPath = iTestResult.getTestContext().getCurrentXmlTest().getParameter("propPath");
		String excelPath = iTestResult.getTestContext().getCurrentXmlTest().getParameter("excelPath");
		
		//Getting Method Name from results
		String methodName = iTestResult.getTestContext().getName().toString();

		deviceIndex = Integer.parseInt(index);
		sheetIndex = Integer.parseInt(sheet);

		deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propPath, excelPath);

		takeScreenshot(deviceIndex, deviceName, methodName);

	}
	
	//Executes when Test get skipped
	public void onTestSkipped(ITestResult iTestResult) {
	}

	//Executes when specific % of Success
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	}

	public void onStart(ITestContext iTestContext) {
	}

}
