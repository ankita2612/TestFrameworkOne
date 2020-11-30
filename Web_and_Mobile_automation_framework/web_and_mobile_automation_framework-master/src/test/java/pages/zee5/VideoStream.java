package pages.zee5;

import org.openqa.selenium.By;
import org.testng.Assert;

import utils.AppiumUtils;

public class VideoStream extends AppiumUtils {

	public static class VideoStreamLocators {

		public static final By downloadButton = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'player_download_button')]");
		public static final By okButton = By
				.xpath("//android.widget.TextView[contains(@resource-id, 'player_dialog_ok_text')]");
		public static final By loginOption = By.xpath("//android.widget.TextView[@text='Click here to login']");
	}

	public boolean verifyDownloadButton(int deviceIndex) {
		waitForElementPresense(VideoStreamLocators.downloadButton, deviceIndex);
		return isElementPresent(VideoStreamLocators.downloadButton, deviceIndex);
	}

	public boolean verifyOK_button(int deviceIndex) {
		waitForElementsPresence(VideoStreamLocators.okButton, deviceIndex);
		return isElementPresent(VideoStreamLocators.okButton, deviceIndex);
	}

	public void clickDownload_button(int deviceIndex, String deviceName) {
		clickElement(VideoStreamLocators.okButton, deviceIndex, deviceName);
		logger.info("Clicked on Download button on device: " + deviceName);
	}

	public boolean verifyLoginOption_button(int deviceIndex) {
		waitForElementsPresence(VideoStreamLocators.loginOption, deviceIndex);
		return isElementPresent(VideoStreamLocators.loginOption, deviceIndex);
	}

	public void startDownload(int deviceIndex, String deviceName) {

		try {
			if (isElementPresent(VideoStreamLocators.downloadButton, deviceIndex)) {

				clickElement(VideoStreamLocators.downloadButton, deviceIndex, deviceName);
				Thread.sleep(2000);
				clickElement(VideoStreamLocators.okButton, deviceIndex, deviceName);
				Thread.sleep(10000);
				Assert.assertFalse(verifyDownloadButton(deviceIndex),
						"Download button is present on video streaming screen.");
				String time = currentDeviceTime(deviceIndex);
				logger.info("Downloading started at " + time + " on device: " + deviceName);
			} else {
				logger.info("Download button does not appear on device: " + deviceName);
			}
		} catch (Exception e) {
			logger.error("Exception occured while downloading the video on device: " + deviceName + e.getMessage());
		}
	}
}
