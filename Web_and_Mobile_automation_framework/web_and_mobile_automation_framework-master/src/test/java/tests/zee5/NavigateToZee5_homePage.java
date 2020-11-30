package tests.zee5;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.zee5.Zee5HomePage;
import pages.zee5.Zee5ListToWatch;
import utils.AppiumUtils;

public class NavigateToZee5_homePage extends AppiumUtils {

	public static String propertiesFilePath;
	public static String excelFilePath;
	public static int sheetIndex;

	Zee5ListToWatch objZeeList = new Zee5ListToWatch();
	Zee5HomePage objZeeHomePage = new Zee5HomePage();

	@Test
	@Parameters({ "index", "sheet", "propPath", "excelPath" })
	public void navigateToZee5_homePage(String index, String sheet, String propPath, String excelPath) {
		
		propertiesFilePath = propPath;
		excelFilePath = excelPath;
		sheetIndex = Integer.parseInt(sheet);
		int deviceIndex = Integer.parseInt(index);
		String deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);

		objZeeList.scroll_and_chooseMovies(deviceIndex, deviceName);
		objZeeHomePage.verifySugarBoxPopUp(prop.getProperty("phoneNumber"), deviceIndex, deviceName);
		Assert.assertTrue(objZeeHomePage.verifySB_ToggleBtn(deviceIndex, deviceName),
				"SB Toggle button does not appear on Home Page on device: " + deviceName);
	}
}
