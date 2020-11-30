package utils;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.Connection;

@Listeners(TestListener.class)

public class AppiumUtils {

	public static AndroidDriver<AndroidElement>[] driver;
	protected static HSSFSheet sheet;
	protected static Logger logger = LogManager.getLogger(AppiumUtils.class.getName());
	public static TouchAction action;
	protected static Properties prop;
	protected static String basePath = System.getProperty("user.dir");
	protected static String propPath;
	protected static String excelPath;

	// To read properties from .properties file
	public void propFile(String propPath) {
		try {
			prop = new Properties();
			FileInputStream inputfile = new FileInputStream(basePath + propPath);
			prop.load(inputfile);
		} catch (FileNotFoundException Ex) {
			logger.error("File not found: " + Ex.getMessage());

		} catch (IOException Ex) {
			logger.error("Exception occurred while loading Properties file: " + Ex.getMessage());
		}
	}

	// To read Excel sheet which will return sheet object
	public HSSFSheet readExcelFile(int sheetIndex, String propPath, String excelPath) {
		try {
			propFile(propPath);
			File objFile = new File(basePath + excelPath);
			FileInputStream inputFile = new FileInputStream(objFile);
			HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
			sheet = workBook.getSheetAt(sheetIndex);
			return sheet;
		} catch (Exception Ex) {
			logger.error("Exception occured while reading Excel file :" + Ex.getMessage());
		}
		return sheet;
	}

	// To set server, device capabilities and launch the app
	public void setCapabilities(String appActivity, String appPackage, int deviceIndex, int sheetIndex,
			String deviceName, String propPath, String excelPath) throws Exception {

		driver = new AndroidDriver[10];
		readExcelFile(sheetIndex, propPath, excelPath);
		logger.info("Current device index " + deviceIndex + " for device: " + deviceName);

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("newCommandTimeout", 0);
		cap.setCapability("deviceName", getStringCellValue(deviceIndex, 0, sheetIndex, propPath, excelPath));
		cap.setCapability("udid", getStringCellValue(deviceIndex, 1, sheetIndex, propPath, excelPath));
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", getStringCellValue(deviceIndex, 2, sheetIndex, propPath, excelPath));
		cap.setCapability("appPackage", appPackage);
		cap.setCapability("appActivity", appActivity);
		// cap.setCapability("Reset", "true");
		cap.setCapability("autoGrantPermissions", true);
		cap.setCapability("clearSystemFiles", true);

		driver[deviceIndex] = new AndroidDriver<AndroidElement>(
				new URL(getStringCellValue(deviceIndex, 3, sheetIndex, propPath, excelPath)), cap);
		driver[deviceIndex].manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("App is getting started on device: " + deviceName);
	}

	// To turn ON wifi
	public void turnOnWIFI(int deviceIndex) {

		driver[deviceIndex].setConnection(Connection.WIFI);
		// assertEquals(Connection.WIFI, driver[deviceIndex].getConnection());
		logger.info("WIFI is turned ON.");
	}

	public void turnOffAllConnections(int deviceIndex) {

		driver[deviceIndex].setConnection(Connection.NONE);
		assertEquals(Connection.ALL, driver[deviceIndex].getConnection());
		logger.info("All connections are turned OFF");
	}

	public void turnOnMobileData(int deviceIndex) {

		driver[deviceIndex].setConnection(Connection.DATA);
		assertEquals(Connection.DATA, driver[deviceIndex].getConnection());
		logger.info("Mobile data is turned ON");
	}

	// To find an element
	public WebElement findElement(By locator, int deviceIndex, String deviceName) {
		return driver[deviceIndex].findElement(locator);
	}

	// To click on an element
	public void clickElement(By locator, int deviceIndex, String deviceName) {

		try {
			driver[deviceIndex].findElement(locator).click();
		} catch (Exception Ex) {

			logger.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
		}
	}

	// To verify, if element is displayed and then click
	public void verifyAndClick(By locator, int deviceIndex, String deviceName) {

		if (driver[deviceIndex].findElement(locator).isDisplayed()) {
			clickElement(locator, deviceIndex, deviceName);
		} else {
			logger.error("Element " + locator + "is not displayed");
		}
	}

	// To click multiple elements having same xpath
	public void chooseMultipleElements(By locator, int deviceIndex) {

		List<AndroidElement> element = driver[deviceIndex].findElements(locator);

		for (AndroidElement elements : element) {
			elements.click();
		}
	}

	// Returns string cell value of Excel sheet
	public String getStringCellValue(int deviceIndex, int cellIndex, int sheetIndex, String propPath,
			String excelPath) {
		readExcelFile(sheetIndex, propPath, excelPath);
		return sheet.getRow(deviceIndex).getCell(cellIndex).getStringCellValue();
	}

	// To click, clear and then enter text in element
	public void sendKeysInTextBox(By locator, String enterText, int deviceIndex) {
		try {

			driver[deviceIndex].findElement(locator).click();
			driver[deviceIndex].findElement(locator).clear();
			driver[deviceIndex].findElement(locator).sendKeys(enterText);
		} catch (Exception Ex) {
			logger.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
		}
	}

	// Returns TEXT of and Element
	public String getElementText(By locator, int deviceIndex, String deviceName) {

		try {
			return driver[deviceIndex].findElement(locator).getText();
		} catch (Exception e) {
			logger.error("Exception occured while getting Element: " + locator + " text on device: " + deviceName);
		}
		return driver[deviceIndex].findElement(locator).getText();
	}

	// To check if element is Enabled or not
	public boolean isEnabled(By locator, int deviceIndex) {

		return driver[deviceIndex].findElement(locator).isEnabled();
	}

	// To check if element is Selected or not
	public boolean isSelected(By locator, int deviceIndex) {

		return driver[deviceIndex].findElement(locator).isSelected();
	}

	// Returns current device time
	public String currentDeviceTime(int deviceIndex) {

		return driver[deviceIndex].getDeviceTime();
	}

	// To take screenshot
	public void takeScreenshot(int deviceIndex, String deviceName, String methodName) {
		try {
			String path_screenshot = basePath + "/mob_screenshots/";
			String deviceTime = currentDeviceTime(deviceIndex);
			File scrFile = ((TakesScreenshot) driver[deviceIndex]).getScreenshotAs(OutputType.FILE);
			File targetFile = new File(path_screenshot + deviceName + " _ " + methodName + " _ " + deviceTime + ".jpg");
			FileUtils.copyFile(scrFile, targetFile);
		} catch (Exception Ex) {
			logger.error("Exception occured while taking screenshot" + Ex.getMessage());
		}
	}

	// To tap on element with X, Y coordinates and wait time
	public void tapByCoordinates(int x, int y, int deviceIndex) {

		new TouchAction(driver[deviceIndex]).tap(x, y).waitAction().perform();
	}

	// defining waitForElementsPresense with explicit wait
	public WebElement waitForElementsPresence(By locator, int deviceIndex) {

		WebDriverWait wait = new WebDriverWait(driver[deviceIndex], 20);
		try {
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception Ex) {
			logger.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
		}
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// To wait for an element and then click on it
	public void waitAndClickElement(By locator, int deviceIndex) {
		waitForElementsPresence(locator, deviceIndex).click();
	}

	// defining waitForElementsVisibility with explicit wait
	public WebElement waitForElementsVisibility(By locator, int deviceIndex) {

		WebDriverWait wait = new WebDriverWait(driver[deviceIndex], 20);
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception Ex) {
			logger.error("Exception Occured While Location The Element: " + Ex.getMessage());
		}
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Returns True/False based on visibility of element
	public boolean waitForElementVisibility(By locator, int deviceIndex) {

		WebDriverWait wait = new WebDriverWait(driver[deviceIndex], 20);
		boolean flag = true;
		try {
			wait.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception Ex) {
			flag = false;
			logger.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
		}
		return flag;
	}

	// Returns True/False based on presence of element
	public boolean waitForElementPresense(By locator, int deviceIndex) {

		WebDriverWait wait = new WebDriverWait(driver[deviceIndex], 20);
		boolean flag = true;
		try {
			wait.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception Ex) {
			flag = false;
			logger.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
		}
		return flag;
	}

	// To scroll page vertical with two points (x,y and x,z) position
	public void scrollVertical(int x, int y, int z, int deviceIndex) {

		action = new TouchAction(driver[deviceIndex]);
		action.press(x, y).waitAction().moveTo(x, z).release().perform();
	}

	// To scroll page horizontal with two points (x,y and x,z) position
	public void scrollHorizontal(int x, int y, int z, int deviceIndex) {

		action = new TouchAction(driver[deviceIndex]);
		action.press(x, y).waitAction().moveTo(z, y).release().perform();
	}

	// To scroll page until an element appear on screen with while loop
	public void scrollVertical_tillElemenetAppear(By locator, int x, int y, int z, int deviceIndex, String deviceName) {
		try {
			int count = 0;
			while (!isElementPresent(locator, deviceIndex)) {
				scrollVertical(x, y, z, deviceIndex);
				y += 2;
				z += 2;
				count++;
				if (count == 30) {
					logger.info("scrolled 30 times but element doesn't appear on page on device: " + deviceName);
					tearDown(deviceIndex, deviceName);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occured while locating the element: " + e.getMessage());
		}
	}

	// Return true/false based on presence on element on screen
	public boolean isElementPresent(By locator, int deviceIndex) {
		boolean flag = true;
		try {
			waitForElementsPresence(locator, deviceIndex);
			driver[deviceIndex].findElement(locator);
		} catch (Exception e) {
			flag = false;
			logger.error("Exception occured while locating the element: " + e.getMessage());
		}
		return flag;
	}

	// Return true/false based on if element displayed on screen
	public boolean isElementDisplayed(By locator, int deviceIndex) {
		boolean isDisplayed = false;
		if (isElementPresent(locator, deviceIndex)) {
			driver[deviceIndex].findElement(locator).isDisplayed();
			isDisplayed = true;
		}
		return isDisplayed;
	}

	// To close the app and quit all current driver's instance
	public void tearDown(int deviceIndex, String deviceName) {

		if (driver[deviceIndex] != null) {
			logger.info("Closing the app on device: " + deviceName);
			driver[deviceIndex].closeApp();
			driver[deviceIndex].quit();
		}
	}

	// To hide device keyboard
	public void hideKeyboard(int deviceIndex) {
		driver[deviceIndex].hideKeyboard();
	}

	// To navigate back screen
	public void pressMobileBackBtn(int deviceIndex) {
		driver[deviceIndex].pressKeyCode(AndroidKeyCode.BACK);
	}

	// To press Enter key
	public void pressEnterBtn(int deviceIndex) {
		driver[deviceIndex].pressKeyCode(AndroidKeyCode.ENTER);
	}

	// To get device height for scrolling purpose
	public int getHeight(int deviceIndex) {
		Dimension windowSize = driver[deviceIndex].manage().window().getSize();
		int height;
		if (windowSize.height < 1000) {
			height = windowSize.height - 50;
		} else {
			height = windowSize.height / 2;
		}
		return height;
	}

	// To scroll and then click on Visible Text
	public void scrollAndClick(String visibleText, int deviceIndex) {
		driver[deviceIndex].findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ visibleText + "\").instance(0))")
				.click();
	}

	// Assertion for Presense of Element
	public void elementPresentAssertion(By locator, int deviceIndex, String message) {

		Assert.assertTrue(isElementPresent(locator, deviceIndex), message);
		logger.error("Assertion fails while verifying Presence on Element and Locator is ==> " + locator);
	}
}