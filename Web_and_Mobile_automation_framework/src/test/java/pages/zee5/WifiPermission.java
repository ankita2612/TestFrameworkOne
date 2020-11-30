package pages.zee5;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class WifiPermission extends AppiumUtils {

	public static class WifiPermission_locators {

		public static final By allowPermission = By.xpath("//android.widget.Button[contains(@resource-id,'button1')]");
	}

	public void allowWifiPermission(int deviceIndex, String deviceName) {
		clickElement(WifiPermission_locators.allowPermission, deviceIndex, deviceName);
	}

	public void allowWifiPermission_Oppo(int deviceIndex, String deviceName) {

		if (deviceName.contains("Oppo") && isElementPresent(WifiPermission_locators.allowPermission, deviceIndex)) {
			allowWifiPermission(deviceIndex, deviceName);
			logger.info("allowed on/off wi-fi on device: " + deviceName);
		}
	}
}
