package pages.zee5;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class Zee5AppDrawer extends AppiumUtils {

	Zee5HomePage objZeeHomePage = new Zee5HomePage();

	public static class Zee5AppDrawerLocators {

		public static final By loginRegisterBtn = By.xpath("//android.widget.TextView[@text='Login/Register']");
		public static final By guestUser = By.xpath("//android.widget.TextView[@text='Guest User']");
		public static final By myDownloads = By.xpath("//android.widget.TextView[@text='My Downloads']");
		public static final By moviesOption = By.xpath("//android.widget.TextView[@text='Movies']");
		public static final By settingsOption = By.xpath("//android.widget.TextView[@text='Settings']");
	}

	public void clickLogin_RegisterBtn(int deviceIndex) {
		waitAndClickElement(Zee5AppDrawerLocators.loginRegisterBtn, deviceIndex);
	}

	public void chooseSettings(int deviceIndex, String deviceName) {

		waitForElementsPresence(Zee5AppDrawerLocators.settingsOption, deviceIndex);
		clickElement(Zee5AppDrawerLocators.settingsOption, deviceIndex, deviceName);
		logger.info("Navigated to Settings section on  device: " + deviceName);
	}

	public void chooseMyDownloads(int deviceIndex, String deviceName) {

		waitForElementsPresence(Zee5AppDrawerLocators.myDownloads, deviceIndex);
		clickElement(Zee5AppDrawerLocators.myDownloads, deviceIndex, deviceName);
		logger.info("Navigated to My Downloads section on  device: " + deviceName);
	}

	public void scroll_and_chooseSettings(int deviceIndex, String deviceName) {
		try {
			waitForElementsPresence(Zee5AppDrawerLocators.moviesOption, deviceIndex);
			int height = getHeight(deviceIndex);
			scrollVertical_tillElemenetAppear(Zee5AppDrawerLocators.settingsOption, 368, height, 450, deviceIndex,
					deviceName);
			chooseSettings(deviceIndex, deviceName);
		} catch (Exception e) {
			logger.error("Exception occured while navigating to Settings section." + e.getMessage());
		}

	}

	public void scroll_and_chooseMyDownloads(int deviceIndex, String deviceName) {
		int height = getHeight(deviceIndex);
		try {
			if (isElementPresent(Zee5AppDrawerLocators.moviesOption, deviceIndex)) {
				scrollVertical_tillElemenetAppear(Zee5AppDrawerLocators.myDownloads, 368, height, 450, deviceIndex,
						deviceName);
				chooseMyDownloads(deviceIndex, deviceName);
			} else {
				scrollVertical_tillElemenetAppear(Zee5AppDrawerLocators.moviesOption, 368, height,
						height + (height / 2), deviceIndex, deviceName);
				scrollVertical_tillElemenetAppear(Zee5AppDrawerLocators.myDownloads, 368, height, 450, deviceIndex,
						deviceName);
				chooseMyDownloads(deviceIndex, deviceName);
			}

		} catch (Exception e) {
			logger.error("Exception occured while navigating to My Downloads section." + e.getMessage());
		}

	}

	public void navigate_myDownloads_from_playBackScrn(int deviceIndex, String deviceName) {

		pressMobileBackBtn(deviceIndex);
		objZeeHomePage.openAppDrawer(deviceIndex, deviceName);
		chooseMyDownloads(deviceIndex, deviceName);
	}
}
