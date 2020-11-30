package pages.zee5;

import org.openqa.selenium.By;
import org.testng.Assert;

import utils.AppiumUtils;

public class SugarBoxPopUp extends AppiumUtils {

	// Sugar Box popup Object Locators
	public static class SugarBoxConnectPopup {

		public static final By connectToSugarBoxBtn = By.xpath("//android.widget.Button[@text='CONNECT TO SUGARBOX']");
		public static final By useMobileDataBtn = By.xpath("//android.widget.Button[@text='USE MOBILE DATA']");
		public static final By useLocalWifiBtn = By.xpath("//android.widget.Button[@text='USE LOCAL WIFI']");
		public static final By learnMoreBtn = By.xpath("//android.widget.TextView[contains(@text,'Learn More')]");
	}

	public static class NetworkSettings {

		public static final By wifiSettings = By.xpath("//com.android.settings[@text='Select Wi‑Fi']");
	}

	// To click on Connect To SugarBox button
	public void clickConnectSugarBox(int deviceIndex, String deviceName) {
		waitForElementsPresence(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex);
		clickElement(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex, deviceName);
	}

	// To verify presence of Connect To SB button
	public boolean verifyConnectSBButton(int deviceIndex) {
		waitForElementsPresence(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex);
		return isElementPresent(SugarBoxConnectPopup.connectToSugarBoxBtn, deviceIndex);
	}

	public boolean verifyWifiSettingsPage(int deviceIndex, String deviceName) {
		waitForElementsPresence(NetworkSettings.wifiSettings, deviceIndex);
		return isElementPresent(NetworkSettings.wifiSettings, deviceIndex);
	}

	// To verify Buttons on SB popup
	public void verifyButtonsON_SB_popup(int deviceIndex, String deviceName) {

		Assert.assertTrue(isElementPresent(SugarBoxConnectPopup.learnMoreBtn, deviceIndex),
				"Learn More button is not pressent on SB popup on device: " + deviceName);
		logger.info("Learn More button is present on SB popup on device: " + deviceName);
		Assert.assertTrue(isElementPresent(SugarBoxConnectPopup.useLocalWifiBtn, deviceIndex),
				"Use Local Wifi button is not pressent on SB popup on device: " + deviceName);
		logger.info("Use Local Wifi button is present on SB popup on device: " + deviceName);
		Assert.assertTrue(isElementPresent(SugarBoxConnectPopup.useMobileDataBtn, deviceIndex),
				"Use Mobile Data button is not pressent on SB popup on device: " + deviceName);
		logger.info("Use Mobile Data button is present on SB popup on device: " + deviceName);
	}

	// To verify Buttons on SB popup and Click on Connect to SB
	public void verifySBPopup_clickConnectSB(int deviceIndex, String deviceName) {

		verifyButtonsON_SB_popup(deviceIndex, deviceName);
		clickConnectSugarBox(deviceIndex, deviceName);
	}

	// To click learn more button
	public void clickLearnMore(int deviceIndex, String deviceName) {
		waitAndClickElement(SugarBoxConnectPopup.learnMoreBtn, deviceIndex);
	}

	// To click on "Use Local Wifi" button and then navigate to App again
	public void clickUseLocal_WifiBtn_Navigate_toApp(int deviceIndex, String deviceName) {
		waitAndClickElement(SugarBoxConnectPopup.useLocalWifiBtn, deviceIndex);
		Assert.assertTrue(verifyWifiSettingsPage(deviceIndex, deviceName),
				"Select Wi-Fi screen doesn't appear on device: " + deviceName);
		pressMobileBackBtn(deviceIndex);
	}
}