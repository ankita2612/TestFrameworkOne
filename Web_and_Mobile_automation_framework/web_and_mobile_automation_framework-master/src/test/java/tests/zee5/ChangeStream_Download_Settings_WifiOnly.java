package tests.zee5;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.zee5.Zee5AppDrawer;
import pages.zee5.Zee5HomePage;
import pages.zee5.Zee5Settings;
import utils.AppiumUtils;

public class ChangeStream_Download_Settings_WifiOnly extends AppiumUtils {

	public static String propertiesFilePath;
	public static String excelFilePath;
	public static int sheetIndex;

	Zee5HomePage objZeeHomePage = new Zee5HomePage();
	Zee5AppDrawer objZeeAppDraw = new Zee5AppDrawer();
	Zee5Settings objZee5Sett = new Zee5Settings();

	@Test
	@Parameters({ "index" })
	public void changeStream_Download_Settings_WifiOnly(String index) {

		int deviceIndex = Integer.parseInt(index);
		String deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);
		
		objZeeHomePage.verifyPremiumMovie_present(deviceIndex, deviceName);
		objZeeHomePage.openAppDrawer(deviceIndex, deviceName);
		objZeeAppDraw.scroll_and_chooseSettings(deviceIndex, deviceName);
		objZee5Sett.chooseWifiOnly_Stream_Download_navigate_to_HomePage(deviceIndex, deviceName);
	}
}
