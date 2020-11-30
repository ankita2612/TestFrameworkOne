package pages.zee5;

import org.openqa.selenium.By;

import pages.zee5.SugarBoxPopUp.SugarBoxConnectPopup;
import utils.AppiumUtils;

public class Zee5HomePage extends AppiumUtils {

	SugarBoxPopUp objSBPopup = new SugarBoxPopUp();
	RegisterSugarBox objRegisterSB = new RegisterSugarBox();
	WifiPermission objWifiPermission = new WifiPermission();

	// Zee5 home page locators
	public static class ZeeHomePage {

		public static final By appLogo = By.xpath("//android.widget.ImageView[contains(@resource-id, 'app_logo')]");
		public static final By movieButton = By.xpath("//android.widget.TextView[@text='MOVIES']");
		public static final By sbToggleBtn = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'app_sugarbox')]");
		public static final By newReleasesTitle = By.xpath("//android.widget.TextView[@text='New Releases']");
		public static final By disableSBIcon = By
				.xpath("//android.widget.ImageView[contains(@resource-id,'sb_icon') and @index='2']");
		public static final By violetSBIcon = By
				.xpath("//android.widget.ImageView[contains(@resource-id,'sb_icon') and @index='1']");
		public static final By ragini = By.xpath("//android.widget.TextView[@text='Ragini MMS 2']");
		public static final By appDrawer = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'action_menu')]");
		public static final By moreOptionForVideo = By
				.xpath("//android.widget.ImageView[contains(@resource-id,'overflow_menu')]");
		public static final By onlyOnZee5_label = By.xpath("//android.widget.TextView[@text='Only On ZEE5']");
		public static final By saveOffline = By.xpath("//android.widget.TextView[@text='Save Offline']");
		public static final By premiumLabelVideo = By.xpath("//android.widget.TextView[@text='Premium']/parent::*");
		public static final By movieTitle = By
				.xpath("//android.widget.TextView[contains(@resource-id,'episode_title')]");
		public static final By searchBtn = By
				.xpath("//android.widget.ImageView[contains(@resource-id,'action_bar_search')]");

	}

	// Verify Zee5 app logo on homepage
	public boolean verifyAppLogo(int deviceIndex) {
		waitForElementPresense(ZeeHomePage.appLogo, deviceIndex);
		return isElementPresent(ZeeHomePage.appLogo, deviceIndex);
	}

	// Verify Zee5 SB Toggle button on homepage
	public boolean verifySB_ToggleBtn(int deviceIndex, String deviceName) {
		waitForElementsPresence(ZeeHomePage.sbToggleBtn, deviceIndex);
		logger.info("SB toggle button appears on Home Page screen on device: " + deviceName);
		return isElementPresent(ZeeHomePage.sbToggleBtn, deviceIndex);
	}

	// Choose movie tab on Zee5 homepage
	public void chooseMovieTab(int deviceIndex, String deviceName) {
		clickElement(ZeeHomePage.movieButton, deviceIndex, deviceName);
	}

	// Verify SB disable icon on videos, it could be premium or incomplete TV shows
	public boolean verifyDisbaleSBIcon(int deviceIndex) {
		waitForElementPresense(ZeeHomePage.disableSBIcon, deviceIndex);
		return isElementPresent(ZeeHomePage.disableSBIcon, deviceIndex);
	}

	// Verify SB violet icon, accessible for all users to stream/download in SB zone
	// once user logged in Zee5 app
	public boolean verifyVioletSBIcon(int deviceIndex) {
		waitForElementPresense(ZeeHomePage.violetSBIcon, deviceIndex);
		return isElementPresent(ZeeHomePage.violetSBIcon, deviceIndex);
	}

	// Play video having disable SB icon
	public void playDisableSBIconMovie(int deviceIndex, String deviceName) {
		waitForElementsPresence(ZeeHomePage.disableSBIcon, deviceIndex);
		clickElement(ZeeHomePage.disableSBIcon, deviceIndex, deviceName);
	}

	// Play video having violet SB icon
	public void playVioletSBIconMovie(int deviceIndex, String deviceName) {
		waitForElementsPresence(ZeeHomePage.violetSBIcon, deviceIndex);
		clickElement(ZeeHomePage.violetSBIcon, deviceIndex, deviceName);
	}

	// Open Zee5 App drawer
	public void openAppDrawer(int deviceIndex, String deviceName) {
		waitForElementsPresence(ZeeHomePage.appDrawer, deviceIndex);
		clickElement(ZeeHomePage.appDrawer, deviceIndex, deviceName);
	}

	// Presence of premium movie based on "Premium" label
	public void verifyPremiumMovie_present(int deviceIndex, String deviceName) {

		String movieTitle = findElement(ZeeHomePage.premiumLabelVideo, deviceIndex, deviceName)
				.findElement(ZeeHomePage.movieTitle).getText();
		logger.info("Premium Label Video ====>> " + movieTitle + " on device: " + deviceName);
	}

	// Scroll home page till disable SB icon tagged video appears on page
	public void scrollHomePageDownFor_DisableSBIcon(int x, int y, int z, int deviceIndex, String deviceName) {

		scrollVertical_tillElemenetAppear(ZeeHomePage.disableSBIcon, x, y, z, deviceIndex, deviceName);
	}

	// Scroll home page till violet SB icon tagged video appears on page
	public void scrollHomePageDownFor_VioletSBIconSBIcon(int x, int y, int z, int deviceIndex, String deviceName) {

		scrollVertical_tillElemenetAppear(ZeeHomePage.violetSBIcon, x, y, z, deviceIndex, deviceName);
	}

	// wait for Zee5 label on home page
	public void waitFor_Zee5Label_on_homePage(int deviceIndex, String deviceName) {

		waitForElementsPresence(ZeeHomePage.onlyOnZee5_label, deviceIndex);
	}

	// scroll, play violet/disable SB icon tagged movie based on "loginUsing"
	// parameter
	public void scroll_and_play_violet_or_premium_movie(int x, int y, int z, int deviceIndex, String deviceName,
			String loginUsing) throws Exception {

		waitFor_Zee5Label_on_homePage(deviceIndex, deviceName);
		for (int i = 0; i < 2; i++) {
			scrollVertical(300, 900, 600, deviceIndex);
		}
		if (loginUsing.contentEquals("phoneNum") || loginUsing.contentEquals("guest")) {

			scrollHomePageDownFor_VioletSBIconSBIcon(x, y, z, deviceIndex, deviceName);
			Thread.sleep(1000);
			playVioletSBIconMovie(deviceIndex, deviceName);

		} else if (loginUsing.contentEquals("emailID")) {

			scrollHomePageDownFor_VioletSBIconSBIcon(x, y, z, deviceIndex, deviceName);
			Thread.sleep(1000);
			playVioletSBIconMovie(deviceIndex, deviceName);
		}
	}

	// To click on Search button on Home Page
	public void clickSearchBtn(int deviceIndex, String deviceName) {
		clickElement(ZeeHomePage.searchBtn, deviceIndex, deviceName);
	}

	// verify SugarBox popup on homepage if it's appear
	public void verifySugarBoxPopUp(String phoneNumber, int deviceIndex, String deviceName) {

		if (isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex)) {

			logger.info("User reached to Home Page and Connect to SugarBox popup appeared on device: " + deviceName);
			objSBPopup.verifyButtonsON_SB_popup(deviceIndex, deviceName);
			objSBPopup.clickConnectSugarBox(deviceIndex, deviceName);
			objRegisterSB.verifyUserLoggedInSB(phoneNumber, deviceIndex, deviceName);
		} else {
			logger.info("Looks like you are already connected to SugarBox or SugarBox server is down on device: "
					+ deviceName);
		}
	}

}
