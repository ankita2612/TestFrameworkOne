package tests.zee5;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.zee5.LoginSignUpPage;
import pages.zee5.MyDownloads;
import pages.zee5.VideoStream;
import pages.zee5.Zee5AppDrawer;
import pages.zee5.Zee5HomePage;
import pages.zee5.Zee5ListToWatch;
import pages.zee5.Zee5LocationPermission;
import utils.AppiumUtils;

public class ConnectToSB_withoutZee5Login extends AppiumUtils {

	// prerequisites: location is ON, location permission is given
	// mobile number registered with devices mac
	public static String propertiesFilePath;
	public static String excelFilePath;
	public static int sheetIndex;

	Zee5LocationPermission objZee5Permission = new Zee5LocationPermission();
	LoginSignUpPage objLoginSignUp = new LoginSignUpPage();
	Zee5ListToWatch objZeeList = new Zee5ListToWatch();
	Zee5HomePage objZeeHomePage = new Zee5HomePage();
	VideoStream objVideoStream = new VideoStream();
	Zee5AppDrawer objZeeAppDraw = new Zee5AppDrawer();
	MyDownloads objMyDownloads = new MyDownloads();

	@BeforeClass
	@Parameters({ "appActivity", "appPackage", "index", "sheet", "propPath", "excelPath" })
	public void setupApp(String appActivity, String appPackage, String index, String sheet, String propPath,
			String excelPath) throws Exception {

		propertiesFilePath = propPath;
		excelFilePath = excelPath;
		int deviceIndex = Integer.parseInt(index);
		sheetIndex = Integer.parseInt(sheet);
		String deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);
		setCapabilities(appActivity, appPackage, deviceIndex, sheetIndex, deviceName, propertiesFilePath,
				excelFilePath);

	}

	@Test
	@Parameters({ "index" })
	public void navigateToZee5_ListToWatch(String index) throws Exception {

		int deviceIndex = Integer.parseInt(index);
		String deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);

		propFile(propertiesFilePath);
		Thread.sleep(2000);
		objLoginSignUp.connectToSB_guestUser_Zee5HomePage(prop.getProperty("phoneNumber"), deviceIndex, deviceName);
		logger.info("Current device name -------->> " + deviceName);
		Assert.assertTrue(objZeeList.verifyHomeOptionPresent(deviceIndex, deviceName),
				"You are not reached to List to watch on device: " + deviceName);
		logger.info("List to watch page is opened on device: " + deviceName);
	}

	@Test(dependsOnMethods = { "navigateToZee5_ListToWatch" })
	@Parameters({ "index" })
	public void navigateToZee5_homePage(String index) {

		int deviceIndex = Integer.parseInt(index);
		String deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);
		objZeeList.scroll_and_chooseMovies(deviceIndex, deviceName);
		objZeeHomePage.verifySugarBoxPopUp(prop.getProperty("phoneNumber"), deviceIndex, deviceName);
		Assert.assertTrue(objZeeHomePage.verifySB_ToggleBtn(deviceIndex, deviceName),
				"SB Toggle button does not appear on Home Page on device: " + deviceName);
	}

	@Test(dependsOnMethods = { "navigateToZee5_homePage" })
	@Parameters({ "index", "x", "y", "z", "loginUsing" })
	public void playVioletMovie(String index, int x, int y, int z, String loginUsing) throws Exception {

		int deviceIndex = Integer.parseInt(index);
		String deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);

		objZeeHomePage.scroll_and_play_violet_or_premium_movie(x, y, z, deviceIndex, deviceName, loginUsing);
		Thread.sleep(10000);
		objVideoStream.clickDownload_button(deviceIndex, deviceName);
		Assert.assertTrue(objVideoStream.verifyLoginOption_button(deviceIndex),
				"Login button doesn't appear on device: " + deviceName);
	}

	@AfterClass
	@Parameters({ "index" })
	public void closeApp(String index) {

		int deviceIndex = Integer.parseInt(index);
		String deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);

		if (driver[deviceIndex] != null) {
			tearDown(deviceIndex, deviceName);
		}

	}
}