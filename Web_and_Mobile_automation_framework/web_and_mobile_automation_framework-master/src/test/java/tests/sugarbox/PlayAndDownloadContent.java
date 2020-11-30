package tests.sugarbox;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.sugarbox.DownloadsPage;
import pages.sugarbox.HomePage;
import pages.sugarbox.PlayerScreen;
import utils.AppiumUtils;

public class PlayAndDownloadContent extends AppiumUtils {

	public static String propertiesFilePath;
	public static String excelFilePath;
	public static int sheetIndex;
	public static int deviceIndex;
	public static String deviceName;

	HomePage objHome = new HomePage();
	DownloadsPage objDownload = new DownloadsPage();
	PlayerScreen objPlayer = new PlayerScreen();

	@Test(priority = 1)
	@Parameters({ "index", "x", "y", "z", "sheet", "propPath", "excelPath" })
	public void playMovie(int index, int x, int y, int z, int sheet, String propPath, String excelPath)
			throws Exception {

		sheetIndex = sheet;
		propertiesFilePath = propPath;
		excelFilePath = excelPath;
		deviceIndex = index;
		deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);

		// Verifying Bottom Bar tabs and Search icon
		objDownload.verifyBottomBar_tabs(deviceIndex, deviceName);
		objDownload.verifyPresenseOf_SearchBtn(deviceIndex, deviceName);

		// Clicking on Movie with Static Movie title via script
		objHome.clickMovie(x, y, z, deviceIndex, deviceName);

		objPlayer.verifyPlayerScreen_locators(deviceIndex, deviceName);
		objPlayer.startPlaying(deviceIndex, deviceName);
		objPlayer.verifyPlayerLocators(deviceIndex, deviceName);
		objPlayer.playContentOnPlayer(deviceIndex, deviceName);
		objPlayer.verifyContentIsPlaying(deviceIndex, deviceName);

		pressMobileBackBtn(deviceIndex);
	}

	@Test(priority = 2)
	@Parameters({ "x", "y", "z" })
	public void downloadMovie(int x, int y, int z) throws Exception {

		objPlayer.startDownloading(deviceIndex, deviceName);

		pressMobileBackBtn(deviceIndex);

		// Verifying Elements on Downloads screen
		objDownload.clickDownloadsTab(deviceIndex, deviceName);

		objDownload.verifyDownloadsSelected(deviceIndex, deviceName);
		objDownload.verifyLocateSBZoneBtn(deviceIndex, deviceName);
		objDownload.verifyContentTitle(deviceIndex, deviceName, prop.getProperty("contentTitle"));
		objDownload.verifyContentDownloadingProgress(deviceIndex, deviceName);

		// Deleting Content on Downloads screen
		objDownload.clickDeleteContent(deviceIndex, deviceName);
		objDownload.verifyDeleteContentPopup_message(deviceIndex, deviceName, prop.getProperty("deletePopupMessage"));
		objDownload.chooseYesDeleteContent(deviceIndex, deviceName);
	}

}
