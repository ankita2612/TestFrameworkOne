package tests.zee5;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.zee5.LoginSignUpPage;
import pages.zee5.Zee5ListToWatch;
import utils.AppiumUtils;

public class Launch_App_NavigateTo_Zee5_ListToWatch extends AppiumUtils {

	// prerequisites: location is ON, location permission is given
	// mobile number registered with devices mac
	public static String propertiesFilePath;
	public static String excelFilePath;
	public static int sheetIndex;

	LoginSignUpPage objLoginSignUp = new LoginSignUpPage();
	Zee5ListToWatch objZeeList = new Zee5ListToWatch();

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
	@Parameters({ "index", "loginUsing" })
	public void navigateToZee5_ListToWatch(String index, String loginUsing) throws Exception {

		int deviceIndex = Integer.parseInt(index);
		String deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);

		propFile(propertiesFilePath);
		Thread.sleep(2000);
		objLoginSignUp.connectToSB_login_ViaPhoneNum_or_ViaEmailID_Zee5_navigateToListTowatch(
				prop.getProperty("phoneNumber"), prop.getProperty("email"), prop.getProperty("phonePassword"),
				prop.getProperty("emailPassword"), deviceIndex, deviceName, loginUsing);
		logger.info("current device name -------->> " + deviceName);
		Assert.assertTrue(objZeeList.verifyHomeOptionPresent(deviceIndex, deviceName),
				"You are not reached to List to watch on device: " + deviceName);
		logger.info("List to watch page is opened on device: " + deviceName);
	}

}
