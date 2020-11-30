package tests.zee5;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.zee5.Zee5HomePage;
import pages.zee5.Zee5SearchScreen;
import utils.AppiumUtils;

public class SearchQueryOnZee5 extends AppiumUtils {

	public static String propertiesFilePath;
	public static String excelFilePath;
	public static int sheetIndex;

	Zee5SearchScreen objZee5Search = new Zee5SearchScreen();
	Zee5HomePage objHomePage = new Zee5HomePage();

	@Test
	@Parameters({ "index", "sheet", "propPath", "excelPath" })
	public void searchQueryOn_Zee5App(String index, String sheet, String propPath, String excelPath) {

		propertiesFilePath = propPath;
		excelFilePath = excelPath;
		sheetIndex = Integer.parseInt(sheet);
		int deviceIndex = Integer.parseInt(index);
		String deviceName = getStringCellValue(deviceIndex, 0, sheetIndex, propertiesFilePath, excelFilePath);

		objHomePage.clickSearchBtn(deviceIndex, deviceName);
		objZee5Search.enterSearchQuery(deviceIndex, deviceName, prop.getProperty("searchQuery"));
		objZee5Search.verifySearchResult(deviceIndex, deviceName);
	}

}
