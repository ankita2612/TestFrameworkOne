package pages.sugarbox;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;

import utils.AppiumUtils;

public class DownloadsPage extends AppiumUtils {

	// Downloads Page locators
	public static class DownloadsPage_locators {

		public static final By homeTab = By.xpath("//android.widget.TextView[@text='Home']");
		public static final By moviesTab = By.xpath("//android.widget.TextView[@text='Movies']");
		public static final By showsTab = By.xpath("//android.widget.TextView[@text='Shows']");
		public static final By downloadsTab = By.xpath("//android.widget.TextView[@text='Downloads']");
		public static final By appDrawer = By.xpath("//android.widget.ImageButton[contains(@content-desc,'Open')]");
		public static final By searchIcon = By.xpath("//android.widget.TextView[contains(@resource-id, 'search')]");
		public static final By locateSBZone = By
				.xpath("//android.widget.Button[contains(@resource-id, 'locateSugarBoxBtn')]");

		public static final By contentTitle = By
				.xpath("//android.widget.TextView[contains(@resource-id, 'downloaded_content_title')]");
		public static final By downloadProgressBar = By
				.xpath("//android.widget.ProgressBar[contains(@resource-id, 'downloaded_progress')]");
		public static final By downloadedPercentage = By
				.xpath("//android.widget.TextView[contains(@resource-id, 'downloaded_tv_progress')]");
		public static final By deleteContent = By
				.xpath("//android.widget.TextView[contains(@resource-id, 'deleteTextView')]");
		public static final By deleteContentPopup_Yes = By.xpath("//android.widget.Button[@text='YES']");
		public static final By deleteContentPopup_No = By.xpath("//android.widget.Button[@text='NO']");
		public static final By deleteContentPopup_text = By
				.xpath("//android.widget.TextView[contains(@resource-id, 'message')]");

	}

	// Verifying bottom bar tabs
	public void verifyBottomBar_tabs(int deviceIndex, String deviceName) {

		elementPresentAssertion(DownloadsPage_locators.homeTab, deviceIndex, "Home Tab is not present at bottom bar.");
		elementPresentAssertion(DownloadsPage_locators.moviesTab, deviceIndex,
				"Movies Tab is not present at bottom bar.");
		elementPresentAssertion(DownloadsPage_locators.showsTab, deviceIndex,
				"Shows Tab is not present at bottom bar.");
		elementPresentAssertion(DownloadsPage_locators.downloadsTab, deviceIndex,
				"Downloads Tab is not present at bottom bar.");
		logger.info("Verified locators on Bottom Bar on device: " + deviceName);
	}

	// Verifying Search icon
	public void verifyPresenseOf_SearchBtn(int deviceIndex, String deviceName) {

		elementPresentAssertion(DownloadsPage_locators.searchIcon, deviceIndex,
				"Search Icon is present on current screen.");
		logger.info("Verified Presense on Search Button on device: " + deviceName);
	}

	// Verifying Locate SB Zone button
	public void verifyLocateSBZoneBtn(int deviceIndex, String deviceName) {

		elementPresentAssertion(DownloadsPage_locators.locateSBZone, deviceIndex,
				"Locate SugarBox Zone button is not present on Downloads screen.");
		logger.info("Verified Locate SB Zone Button on Downloads tab on device: " + deviceName);
	}

	// Verifying if Downloads tab is selected
	public void verifyDownloadsSelected(int deviceIndex, String deviceName) {

		Assert.assertTrue(isSelected(DownloadsPage_locators.downloadsTab, deviceIndex) == true,
				"Downloads screen is not selected.");
		logger.info("Verified Downloads tab is selected on device: " + deviceName);
	}

	// Clicking on Downloads tab
	public void clickDownloadsTab(int deviceIndex, String deviceName) {

		clickElement(DownloadsPage_locators.downloadsTab, deviceIndex, deviceName);
		logger.info("User clicked on Downloads tab on device: " + deviceName);
	}

	// Returns Content title
	public String getDownloadingContentTitle(int deviceIndex, String deviceName) {

		logger.info("Getting Content Title on Downloads page on device: " + deviceName);
		return getElementText(DownloadsPage_locators.contentTitle, deviceIndex, deviceName);
	}

	// Verifying Content Title
	public void verifyContentTitle(int deviceIndex, String deviceName, String contentTitle) {

		Assert.assertTrue(getDownloadingContentTitle(deviceIndex, deviceName).equalsIgnoreCase(contentTitle),
				"Downloading Content Title is not matching on Downloads screen on device: " + deviceName);
		logger.info("Content Title verified on Downloads screen on device: " + deviceName);
	}

	// Returns Message text on Delete Content Popup
	public String getDeletePopup_message(int deviceIndex, String deviceName) {

		logger.info("Getting Delete Content Popup's Message on Downloads page on device: " + deviceName);
		return getElementText(DownloadsPage_locators.deleteContentPopup_text, deviceIndex, deviceName);
	}

	// Verifying Message text on Delete Content Popup
	public void verifyDeleteContentPopup_message(int deviceIndex, String deviceName, String deletePopupMessage) {

		Assert.assertTrue(getDeletePopup_message(deviceIndex, deviceName).equalsIgnoreCase(deletePopupMessage),
				"Message text is not matching on Delete Content Popup on device: " + deviceName);
		logger.info("Message text is verified on Delete Content Popup on device: " + deviceName);
	}

	// Clicking on Delete Content Button
	public void clickDeleteContent(int deviceIndex, String deviceName) {

		clickElement(DownloadsPage_locators.deleteContent, deviceIndex, deviceName);
		logger.info("User clicked on Delete button to delete content on Downloads screen on device: " + deviceName);
	}

	// Choosing Yes to Delete content on Downloads screen
	public void chooseYesDeleteContent(int deviceIndex, String deviceName) {

		clickElement(DownloadsPage_locators.deleteContentPopup_Yes, deviceIndex, deviceName);
		logger.info("User clicked on Yes button to delete content on Downloads screen on device: " + deviceName);
	}

	// Returns Downloaded Percentage
	public String getDownloadingProgress(int deviceIndex, String deviceName) {

		logger.info("Getting content's downloading progress on Downloads screen on device: " + deviceName);
		return getElementText(DownloadsPage_locators.downloadedPercentage, deviceIndex, deviceName);
	}

	// Verifying Content's downloading progress
	public void verifyContentDownloadingProgress(int deviceIndex, String deviceName) throws Exception {

		TimeUnit.MINUTES.sleep(1);

		String downloadPercentage1 = getDownloadingProgress(deviceIndex, deviceName);

		TimeUnit.MINUTES.sleep(1);

		String downloadPercentage2 = getDownloadingProgress(deviceIndex, deviceName);

		if (downloadPercentage1.equalsIgnoreCase("0%")) {

			logger.info("Downloading is not started yet on Downloads screen on device: " + deviceName);

		} else if (downloadPercentage2.equalsIgnoreCase(downloadPercentage1)) {

			logger.info("Downloading is started and progressed till: " + downloadPercentage2
					+ " on Downloads screen on device: " + deviceName);
		} else {

			logger.info("Content is getting downloaded and progressed till: " + downloadPercentage2
					+ " on Downloads screen on device: " + deviceName);
		}

		logger.info("Yay!! Content is downloading on Downloads screen on device: " + deviceName);
	}
}
