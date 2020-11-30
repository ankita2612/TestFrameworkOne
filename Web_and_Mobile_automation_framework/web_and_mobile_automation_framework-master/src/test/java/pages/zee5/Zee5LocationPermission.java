package pages.zee5;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class Zee5LocationPermission extends AppiumUtils {

	public static class GivePermission {

		public static final By givePermissionBtn = By
				.xpath("//android.widget.Button[contains(@resource-id,'btn_proceed')]");

		public static final By allowBtn = By.xpath("//android.widget.Button[@text='ALLOW']");
	}

	public static class welcomeToZee {

		public static final By imageview = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'decorationImageView')]");
		public static final By skipBtn = By.xpath("//android.widget.TextView[contains(@resource-id, 'welcome_skip')]");
		public static final By updateLanguageBtn = By.xpath("//android.widget.TextView[@text='Update Language']");
	}

	public void giveLocationPermission(int deviceIndex, String deviceName) {

		clickElement(GivePermission.givePermissionBtn, deviceIndex, deviceName);
		clickElement(GivePermission.allowBtn, deviceIndex, deviceName);
		logger.info("Location permission is given on device: " + deviceName);

	}
	
	public void navigateLanguageScreen(int deviceIndex, String deviceName) {
		
		waitAndClickElement(welcomeToZee.updateLanguageBtn, deviceIndex);
		logger.info("Navigated to Zee5 language screen page on device: " + deviceName);
	}
}
