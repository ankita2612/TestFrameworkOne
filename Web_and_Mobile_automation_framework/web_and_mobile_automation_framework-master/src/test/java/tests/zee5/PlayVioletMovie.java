package tests.zee5;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.zee5.MyDownloads;
import pages.zee5.VideoStream;
import pages.zee5.Zee5AppDrawer;
import pages.zee5.Zee5HomePage;
import utils.AppiumUtils;

public class PlayVioletMovie extends AppiumUtils {

	public static String propertiesFilePath;
	public static String excelFilePath;
	public static int sheetIndex;

	Zee5HomePage objZeeHomePage = new Zee5HomePage();
	VideoStream objVideoStream = new VideoStream();
	Zee5AppDrawer objZeeAppDraw = new Zee5AppDrawer();
	MyDownloads objMyDownloads = new MyDownloads();

	@Test
	@Parameters({ "index", "x", "y", "z", "loginUsing", "sheet", "propPath", "excelPath" })
	public void playVioletMovie(String index, int x, int y, int z, String loginUsing, String sheet, String propPath,
			String excelPath) throws Exception {

		int deviceIndex = Integer.parseInt(index);
		String deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);
		propertiesFilePath = propPath;
		excelFilePath = excelPath;
		sheetIndex = Integer.parseInt(sheet);

		objZeeHomePage.openAppDrawer(deviceIndex, deviceName);
		objZeeAppDraw.scroll_and_chooseMyDownloads(deviceIndex, deviceName);
		objMyDownloads.verify_and_deleteMovies_and_navigate_homePage(deviceIndex, deviceName);
		objZeeHomePage.scroll_and_play_violet_or_premium_movie(x, y, z, deviceIndex, deviceName, loginUsing);
		Thread.sleep(10000);
		Assert.assertTrue(objVideoStream.verifyDownloadButton(deviceIndex),
				"Download button doesn't appear on device: " + deviceName);
		objVideoStream.startDownload(deviceIndex, deviceName);
	}

}
