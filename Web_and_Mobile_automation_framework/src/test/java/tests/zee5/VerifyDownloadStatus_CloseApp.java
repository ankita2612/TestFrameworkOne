package tests.zee5;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.zee5.MyDownloads;
import pages.zee5.Zee5AppDrawer;
import utils.AppiumUtils;

public class VerifyDownloadStatus_CloseApp extends AppiumUtils {

	public static String propertiesFilePath;
	public static String excelFilePath;
	public static int sheetIndex;

	Zee5AppDrawer objZeeAppDraw = new Zee5AppDrawer();
	MyDownloads objMyDownloads = new MyDownloads();

	@Test
	@Parameters({ "index" })
	public void verifyDownloadStatus(String index) {

		int deviceIndex = Integer.parseInt(index);
		String deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);

		objZeeAppDraw.navigate_myDownloads_from_playBackScrn(deviceIndex, deviceName);
		Assert.assertTrue(objMyDownloads.verifyMovieSelector(deviceIndex),
				"No movies present in My Downloads section on device: " + deviceName);
		objMyDownloads.verifyDownloadStatus(deviceIndex, deviceName);
		Assert.assertFalse(objMyDownloads.verifyDownloadStatusBtn(deviceIndex),
				"Download is not completed yet on device: " + deviceName);
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
