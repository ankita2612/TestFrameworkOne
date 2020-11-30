package tests.sugarbox;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.sugarbox.DownloadsPage;
import pages.sugarbox.HomePage;
import pages.sugarbox.LoginPage;
import pages.sugarbox.PersonalizationPage;
import pages.sugarbox.TutorialPage;
import utils.AppiumUtils;

public class LoginSB extends AppiumUtils {

	public static String propertiesFilePath;
	public static String excelFilePath;
	public static int sheetIndex;
	public static int deviceIndex;
	public static String deviceName;

	PersonalizationPage objPersonalization = new PersonalizationPage();
	TutorialPage objTutorials = new TutorialPage();
	DownloadsPage objDownload = new DownloadsPage();
	LoginPage objLogin = new LoginPage();
	HomePage objHome = new HomePage();

	@BeforeClass
	@Parameters({ "appActivity", "appPackage", "index", "sheet", "propPath", "excelPath" })
	public void setupApp(String appActivity, String appPackage, int index, int sheet, String propPath, String excelPath)
			throws Exception {

		propertiesFilePath = propPath;
		excelFilePath = excelPath;
		sheetIndex = sheet;
		deviceIndex = index;
		deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);

		setCapabilities(appActivity, appPackage, deviceIndex, sheetIndex, deviceName, propertiesFilePath,
				excelFilePath);
	}

	@Test
	// @Parameters({ "deviceIndex" })
	public void ConnectTOSB_loginSugarBox() {

		// Verifying Elements on Personalization screen
		objPersonalization.verifyPersonalizationPage(deviceIndex, deviceName);

		// Verifying Tutorial screens and Clicking on Done Button to navigate HomePage
		objTutorials.verifyTutorialPage1(deviceIndex);
		objTutorials.verifyTutorialPage2(deviceIndex);
		objTutorials.clickDoneBtn(deviceIndex, deviceName);

		// Verifying Login screen locators and Logging in SB
		objLogin.verifyLoginPageLocators(deviceIndex, deviceName);
		objLogin.verifyMobNo_instructionText(deviceIndex, deviceName);
		objLogin.enterMobileNum(deviceIndex, prop.getProperty("phoneNumber"), deviceName);
		objLogin.clickContinueBtn(deviceIndex, deviceName);

	}

	@AfterSuite
	public void closeApp() {

		tearDown(deviceIndex, deviceName);
	}
}
