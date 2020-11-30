package pages.zee5;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class Zee5Settings extends AppiumUtils {

	MyDownloads objMyDownloads = new MyDownloads();

	// Object locators for Zee5 Settings screen
	public static class Settings_locators {

		public static final By wiFi_only_Streaming = By
				.xpath("(//android.widget.Switch[contains(@resource-id,'settings_switch')])[1]");
		public static final By wiFi_only_Download = By
				.xpath("(//android.widget.Switch[contains(@resource-id,'settings_switch')])[2]");
		public static final By quality_ask_eachTime_download = By
				.xpath("//android.widget.TextView[@text='Ask each time']");
		public static final By wiFi_only_Streaming_Btn = By.xpath(
				"(//android.widget.Switch[@text='‎‏‎‎‎‎‎‏‎‏‏‏‎‎‎‎‎‏‎‎‏‎‎‎‎‏‏‏‏‎‎‏‏‏‎‏‎‏‏‏‎‎‏‎‏‏‎‏‎‏‏‎‏‎‏‏‎‎‏‎‏‏‎‎‏‏‎‎‎‏‏‎‎‎‎‏‏‏‎‏‎‎‎‎‎‏‎‎‏‎OFF‎‏‎‎‏‎'])[1]");
		public static final By wiFi_only_Download_Btn = By.xpath(
				"(//android.widget.Switch[@text='‎‏‎‎‎‎‎‏‎‏‏‏‎‎‎‎‎‏‎‎‏‎‎‎‎‏‏‏‏‎‎‏‏‏‎‏‎‏‏‏‎‎‏‎‏‏‎‏‎‏‏‎‏‎‏‏‎‎‏‎‏‏‎‎‏‏‎‎‎‏‏‎‎‎‎‏‏‏‎‏‎‎‎‎‎‏‎‎‏‎OFF‎‏‎‎‏‎'])[2]");

	}

	// Quality popup locators
	public static class QualityOptions_locators {

		public static final By better_RadioBtn = By.xpath("//android.widget.RadioButton[@text='Better']");
		public static final By best_RadioBtn = By.xpath("//android.widget.RadioButton[@text='Best']");
		public static final By oK_btn = By.xpath("//android.widget.TextView[@text='OK']");
	}

	// Choose Wifi only Stream navigate to HomePage
	public void chooseWifiOnly_Stream_navigate_to_homePage(int deviceIndex, String deviceName) {
		clickElement(Settings_locators.wiFi_only_Streaming, deviceIndex, deviceName);
		pressMobileBackBtn(deviceIndex);

	}

	// Choose Wifi only Download navigate to HomePage
	public void chooseWifiOnly_Download_navigate_to_homePage(int deviceIndex, String deviceName) {
		clickElement(Settings_locators.wiFi_only_Download, deviceIndex, deviceName);
		pressMobileBackBtn(deviceIndex);
	}

	// Choose Wifi only Stream and Download navigate to HomePage
	public void chooseWifiOnly_Stream_Download_navigate_to_HomePage(int deviceIndex, String deviceName) {

		if (isElementPresent(Settings_locators.wiFi_only_Streaming_Btn, deviceIndex)) {
			clickElement(Settings_locators.wiFi_only_Streaming, deviceIndex, deviceName);

			if (isElementPresent(Settings_locators.wiFi_only_Download_Btn, deviceIndex)) {
				clickElement(Settings_locators.wiFi_only_Download, deviceIndex, deviceName);
			}
		} else if (isElementPresent(Settings_locators.wiFi_only_Download_Btn, deviceIndex)) {
			clickElement(Settings_locators.wiFi_only_Download, deviceIndex, deviceName);

		} else {
			logger.info("Wi-Fi only buttons for Streaming and Downloading are ON by default on device: " + deviceName);
		}
		pressMobileBackBtn(deviceIndex);
	}

	// Choose Best and Better Quality Option
	public void choose_Best_Better_qualityOption(int deviceIndex, String deviceName, String dwnld_quality) {
		clickElement(Settings_locators.quality_ask_eachTime_download, deviceIndex, deviceName);
		if (dwnld_quality.contentEquals("best")) {

			clickElement(QualityOptions_locators.best_RadioBtn, deviceIndex, deviceName);
		} else if (dwnld_quality.contentEquals("better")) {

			clickElement(QualityOptions_locators.better_RadioBtn, deviceIndex, deviceName);
		} else {

			clickElement(QualityOptions_locators.better_RadioBtn, deviceIndex, deviceName);
		}
		clickElement(QualityOptions_locators.oK_btn, deviceIndex, deviceName);
		pressMobileBackBtn(deviceIndex);
	}

}
