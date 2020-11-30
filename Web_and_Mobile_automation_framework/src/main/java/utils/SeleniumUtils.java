package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	protected static WebDriver driver;
	protected static Logger logger = LogManager.getLogger(SeleniumUtils.class.getName());
	protected static String basePath = System.getProperty("user.dir");
	protected static Properties prop;
	protected static String propPath;
	protected static String excelPath;
	protected static HSSFSheet sheet;
	protected static JavascriptExecutor js;

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

	// Open different browser by passing browser's value
	public void openBrowser(String browser, String propPath) {

		if (browser.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", basePath + "/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", basePath + "/geckodriver");
			driver = new FirefoxDriver();
		} else if (browser.contains("headlessChrome")) {
			System.setProperty("webdriver.chrome.driver", basePath + "/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
		} else if (browser.contains("mobileEmulator")) {

			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Nexus 5");

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

			driver = new ChromeDriver(chromeOptions);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		logger.info("Browser is getting launched.");
		propFile(propPath);
	}

	// To open URL in browser
	public static void getURL(String url) {
		driver.get(url);
		logger.info("Opening website: " + url + " in browser.");
	}

	// To navigate to specific URL
	public void navigateToURL(String url) {

		driver.navigate().to(url);
	}

	// To find an element
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}

	// To click on an element
	public static void clickElement(By locator) {

		try {
			driver.findElement(locator).click();
		} catch (Exception Ex) {

			logger.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
		}
	}

	// To verify, if element is displayed and then click
	public void verify_If_DisplayedAndClick(By locator) {

		if (driver.findElement(locator).isDisplayed()) {
			clickElement(locator);
		} else {
			logger.error("Element " + locator + "is not displayed");
		}
	}

	// To click, clear and then enter text in element
	public void sendKeysInTextBox(By locator, String enterText) {
		try {

			driver.findElement(locator).click();
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(enterText);
		} catch (Exception Ex) {
			logger.error("Exception Occurred While Locating The Element: " + Ex.getMessage());
		}
	}

	// Returns TEXT of and Element
	public String getElementText(By locator) {

		try {
			return driver.findElement(locator).getText();
		} catch (Exception e) {
			logger.error("Exception occured while getting Element: " + locator);
		}
		return driver.findElement(locator).getText();
	}

	// Find multiple elements and then click via index
	public static void findElements_andClickViaIndex(By locator, int index) {

		driver.findElements(locator).get(index).click();
	}

	// Wait for Visibility of Element and then click
	public static void waitForElementVisibility_AndClick(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
	}

	// Wait for Presence of Element and then click
	public static void waitForElementPresense_AndClick(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
	}

	// Wait for Visibility of Element and then Send Keys
	public static void waitForElementVisibility_AndSendKeys(By locator, String enterText) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(enterText);
	}

	// Wait for Presence of Element and then Send Keys
	public static void waitForElementPresense_AndSendKeys(By locator, String enterText) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(enterText);
	}

	// Wait for Present of Element and then Send Keys with Enter
	public static void waitForElementPresense_AndSendKeys_AndPressEnter(By locator, String enterText) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(enterText, Keys.ENTER);
	}

	// To take screenshot
	public void takeScreenshot(String methodName) {
		try {
			String path_screenshot = basePath + "/web_screenshots/";
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File(path_screenshot + " _ " + methodName + " _ " + ".jpg");
			FileUtils.copyFile(scrFile, targetFile);
		} catch (Exception Ex) {
			logger.error("Exception occured while taking screenshot" + Ex.getMessage());
		}
	}

	// Find multiple elements and then open in new tab
	public static void findMultipleElements_openNewTab(By locator, int index) {

		String clickOnLink = Keys.chord(Keys.CONTROL, Keys.ENTER);
		driver.findElements(locator).get(index).sendKeys(clickOnLink);
	}

	// Traverse to multiple windows
	public static void traverseMultipleWindows() {

		Set<String> currentWindows = driver.getWindowHandles();
		Iterator<String> itr = currentWindows.iterator();

		while (itr.hasNext()) {
			driver.switchTo().window(itr.next());
		}
	}

	// Return true/false based on presence on element on screen
	public static boolean isElementPresent(By locator) {

		boolean flag = true;
		try {
			driver.findElement(locator);
		} catch (Exception e) {
			flag = false;
			logger.error("Exception occured while locating the element: " + e.getMessage());
		}
		return flag;
	}

	// Return true/false if element displayed on screen
	public static boolean isElementDisplayed(By locator) {
		boolean flag = true;
		try {
			driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			flag = false;
			logger.error("Exception occured while locating the element: " + e.getMessage());
		}
		return flag;
	}

	// To get Page title
	public static String getPageTitle() {

		return driver.getTitle();
	}

	// To Close all opened windows for current driver
	public void tearDown() {
		logger.info("Closing the browser");
		driver.quit();
	}

	// Scroll to bottom
	public void scrollPage(int counter) throws InterruptedException {

		String script = "window.scrollTo(0,Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));";
		int count = 0;

		while (count != counter) {

			js = (JavascriptExecutor) driver;
			js.executeScript(script);

			Thread.sleep(1000);
			count++;
		}
	}

	// Select dropDown and then click using Value
	public void selectOption_withValue(By locator, String value) {

		Select dropDown = new Select(findElement(locator));
		dropDown.selectByValue(value);

	}
}