package pages.zee5;

import org.openqa.selenium.By;

import pages.zee5.SugarBoxDisconnected.SBDisconnected;
import pages.zee5.SugarBoxWalkingOut.SBWalkingOutLocators;
import utils.AppiumUtils;

public class MyDownloads extends AppiumUtils {

	SugarBoxDisconnected objSBDisconnect = new SugarBoxDisconnected();
	SugarBoxWalkingOut objSBWalkOut = new SugarBoxWalkingOut();

	// My Downloads object locators
	public static class MyDownloadsLocators {

		public static final By movieSelector = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'download_movies_selector')]");
		public static final By deleteButton = By
				.xpath("//android.widget.RelativeLayout[contains(@resource-id, 'download_delete_layout')]");
		public static final By backButton = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'back_button_download_offline')]");
		public static final By downloadStatus = By
				.xpath("//android.widget.TextView[contains(@resource-id, 'download_status')]");
		public static final By downloadCompleted = By
				.xpath("//android.widget.ProgressBar[contains(@resource-id, 'download_progress')]");
	}

	// Choose all movies present in My Downloads tab
	public void chooseAllMovies(int deviceIndex) {

		chooseMultipleElements(MyDownloadsLocators.movieSelector, deviceIndex);
	}

	// To verify if any movie present in My Downloads tab
	public boolean verifyMovieSelector(int deviceIndex) {
		waitForElementPresense(MyDownloadsLocators.movieSelector, deviceIndex);
		return isElementPresent(MyDownloadsLocators.movieSelector, deviceIndex);
	}

	// To delete selected movies
	public void clickDeleteButton(int deviceIndex, String deviceName) {

		clickElement(MyDownloadsLocators.deleteButton, deviceIndex, deviceName);
	}

	// Click on My Downloads back button
	public void clickBackButton(int deviceIndex, String deviceName) {

		clickElement(MyDownloadsLocators.backButton, deviceIndex, deviceName);
	}

	public boolean verifyDownloadStatusBtn(int deviceIndex) {
		waitForElementPresense(MyDownloadsLocators.downloadStatus, deviceIndex);
		return isElementPresent(MyDownloadsLocators.downloadStatus, deviceIndex);
	}

	public void verifyDownloadStatus(int deviceIndex, String deviceName) {
		try {
			while (isElementPresent(MyDownloadsLocators.downloadStatus, deviceIndex)) {
				String downloadStatus = getElementText(MyDownloadsLocators.downloadStatus, deviceIndex, deviceName);
				logger.info("Current download percentage: " + downloadStatus + " on device: " + deviceName);
				if (downloadStatus.contains("Error")) {
					logger.info("Error occured while downloading video on device: " + deviceName);
					tearDown(deviceIndex, deviceName);
				}
				Thread.sleep(30000);
			}
			if (isElementPresent(SBDisconnected.crossBtn, deviceIndex)) {
				objSBDisconnect.closeDisconnectPopup(deviceIndex, deviceName);
				logger.info("Oops we are disconnected from SugarBox on device: " + deviceName);
			} else if (isElementPresent(SBWalkingOutLocators.walkingOutText, deviceIndex)) {
				objSBWalkOut.verify_and_closeWalkingOutPopup(deviceIndex, deviceName);
				logger.info("Looks like you are walking out of SB zone on device: " + deviceName);
			} else {
				String time = currentDeviceTime(deviceIndex);
				logger.info("Download completed at: " + time + " on device: " + deviceName);
			}
		} catch (Exception e) {
			logger.error("Exception occured while validating download percentage: " + e.getMessage() + " on device: "
					+ deviceName);
		}
	}

	public void verify_and_deleteMovies_and_navigate_homePage(int deviceIndex, String deviceName) {

		if (isElementPresent(MyDownloadsLocators.movieSelector, deviceIndex)) {
			chooseAllMovies(deviceIndex);
			clickDeleteButton(deviceIndex, deviceName);
			clickBackButton(deviceIndex, deviceName);
			logger.info("Movies deleted from My Downloads section on device: " + deviceName);
		} else {
			logger.info("No movies present in My Downloads section on device: " + deviceName);
			clickBackButton(deviceIndex, deviceName);
		}
	}
}
