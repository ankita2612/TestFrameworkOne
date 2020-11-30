package pages.zee5;

import org.openqa.selenium.By;

import pages.zee5.Zee5LocationPermission.GivePermission;
import utils.AppiumUtils;

public class SplashActivity extends AppiumUtils {

	Zee5LocationPermission objZee5LocationPer = new Zee5LocationPermission();

	public static class SplashLocators {

		public static final By okBtnLocationPermission = By.xpath("//android.widget.Button[@text='OK']");

	}

	public void acceptLocationDevicePopup(int deviceIndex, String deviceName) {
		verifyAndClick(SplashLocators.okBtnLocationPermission, deviceIndex, deviceName);
	}

	public void locationPermission(int deviceIndex, String deviceName) {

		System.out.println("verifying if location permisson needs to be given....");

		if (isElementPresent(SplashLocators.okBtnLocationPermission, deviceIndex)) {
			acceptLocationDevicePopup(deviceIndex, deviceName);

		} else if (isElementPresent(GivePermission.givePermissionBtn, deviceIndex)) {
			objZee5LocationPer.giveLocationPermission(deviceIndex, deviceName);
		} else {
			System.out.println("Location permission is already accepted.");
		}
	}
}
