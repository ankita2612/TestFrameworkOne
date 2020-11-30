package pages.zee5;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class SugarBoxWalkingOut extends AppiumUtils {

	public static class SBWalkingOutLocators {

		public static final By walkingOutClose = By
				.xpath("//android.widget.ImageView[contains(@resource-id, 'img_close')]");
		public static final By walkingOutText = By
				.xpath("//android.widget.TextView[contains(@text, 'Walking out of')]");
	}

	public void verify_and_closeWalkingOutPopup(int deviceIndex, String deviceName) {

		if (isElementPresent(SBWalkingOutLocators.walkingOutText, deviceIndex)) {
			clickElement(SBWalkingOutLocators.walkingOutClose, deviceIndex, deviceName);
		}
	}
}
