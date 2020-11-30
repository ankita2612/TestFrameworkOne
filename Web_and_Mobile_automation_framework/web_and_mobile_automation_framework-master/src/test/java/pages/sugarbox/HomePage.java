package pages.sugarbox;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class HomePage extends AppiumUtils {

	// HomePage locators
	public static class homePage_locators {

		public static final By viewAllBtn = By
				.xpath("//android.widget.TextView[contains(@resource-id, 'homefeedViewAll')]");
		public static final By homeTab = By.xpath("//android.widget.TextView[@text='Home']");
		public static final By moviesTab = By.xpath("//android.widget.TextView[@text='Movies']");
		public static final By showsTab = By.xpath("//android.widget.TextView[@text='Shows']");
		public static final By downloadsTab = By.xpath("//android.widget.TextView[@text='Downloads']");
		public static final By appDrawer = By.xpath("//android.widget.ImageButton[contains(@content-desc,'Open')]");
		public static final By searchIcon = By.xpath("//android.widget.TextView[contains(@resource-id, 'search')]");

		public static String contentTileXpath = "//android.widget.TextView[@text='" + prop.getProperty("contentTitle")
				+ "']//parent::android.widget.LinearLayout";
		public static final By movieTile = By.xpath(contentTileXpath);
		public static final By movieTitle = By.xpath(
				"//android.widget.LinearLayout/android.widget.TextView[contains(@resource-id, 'nonFeaturedContentTitleRV')]");

	}

	// Clicking on View All button
	public void clickViewAllBtn(int deviceIndex, String deviceName) {

		clickElement(homePage_locators.viewAllBtn, deviceIndex, deviceName);
		logger.info("User clicked on View All button on device: " + deviceName);
	}

	// Clicking on Movie Tile
	public void clickMovie(int x, int y, int z, int deviceIndex, String deviceName) {

		scrollVertical_tillElemenetAppear(homePage_locators.movieTile, x, y, z, deviceIndex, deviceName);
		clickElement(homePage_locators.movieTile, deviceIndex, deviceName);
		logger.info("User clicked on Movie Tile on device: " + deviceName);
	}

	// Returns Movie Title
	public String getMovieTitle(int deviceIndex, String deviceName) {

		logger.info("Getting Movie Title on Home Page on device: " + deviceName);
		return getElementText(homePage_locators.movieTitle, deviceIndex, deviceName);
	}
}
